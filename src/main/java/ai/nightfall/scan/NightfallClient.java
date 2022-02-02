package ai.nightfall.scan;

import ai.nightfall.scan.model.BaseNightfallException;
import ai.nightfall.scan.model.CompleteFileUploadRequest;
import ai.nightfall.scan.model.FileUpload;
import ai.nightfall.scan.model.InitializeFileUploadRequest;
import ai.nightfall.scan.model.NightfallAPIException;
import ai.nightfall.scan.model.NightfallClientException;
import ai.nightfall.scan.model.NightfallErrorResponse;
import ai.nightfall.scan.model.NightfallRequestTimeoutException;
import ai.nightfall.scan.model.ScanFileRequest;
import ai.nightfall.scan.model.ScanFileResponse;
import ai.nightfall.scan.model.ScanTextRequest;
import ai.nightfall.scan.model.ScanTextResponse;
import ai.nightfall.scan.model.UploadFileChunkRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Provides a client for accessing the Nightfall Developer Platform.
 */
public class NightfallClient implements Closeable {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final long wakeupDurationMillis = Duration.ofSeconds(15).toMillis();
    private static final int DEFAULT_RETRY_COUNT = 5;
    private static final String API_HOST = "https://api.nightfall.ai";
    private final String implVersion = loadImplVersion();

    private final String apiHost;
    private final String apiKey;
    private final int fileUploadConcurrency;
    private final int retryCount;
    private final ExecutorService executor;
    private final OkHttpClient httpClient;

    // package-visible for testing
    NightfallClient(String apiHost, String apiKey, int fileUploadConcurrency, OkHttpClient httpClient) {
        this.apiHost = apiHost;
        this.apiKey = apiKey;
        this.fileUploadConcurrency = fileUploadConcurrency;
        this.retryCount = DEFAULT_RETRY_COUNT;
        this.executor = Executors.newFixedThreadPool(this.fileUploadConcurrency);
        this.httpClient = httpClient;
    }

    private String loadImplVersion() {
        Package pkg = this.getClass().getPackage();
        if (pkg == null) {
            return "";
        }
        return pkg.getImplementationVersion();
    }

    /**
     * Closes this client and releases underlying system resources.
     */
    @Override
    public void close() {
        this.executor.shutdown();
        this.httpClient.dispatcher().executorService().shutdown();
    }

    /**
     * Scans the provided plaintext against the provided detectors, and returns all findings. The response object will
     * contain a list of lists representing the findings. Each index <code>i</code> in the findings array will
     * correspond one-to-one with the input request payload list, so all findings stored in a given sub-list refer to
     * matches that occurred in the <code>i</code>th index of the request payload.
     *
     * @param request the data to scan, along with the configuration describing how to scan the data. The
     *                request payload may not exceed 500KB.
     * @return an object containing the findings from each item in the request payload
     * @throws NightfallAPIException thrown if a non-2xx status code is returned by the API.
     * @throws NightfallClientException thrown if a I/O error occurs while processing the request
     * @throws IllegalArgumentException thrown if <code>request</code> is null
     * @throws NightfallRequestTimeoutException thrown if the request is aborted because the timeout is exceeded
     */
    public ScanTextResponse scanText(ScanTextRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("request must be non-null");
        }
        byte[] jsonBody;
        try {
            jsonBody = objectMapper.writeValueAsBytes(request);
        } catch (JsonProcessingException e) {
            throw new NightfallClientException("processing scan request: " + e.getMessage());
        }

        MediaType json = MediaType.parse("application/json");
        return this.issueRequest("/v3/scan", "POST", json, jsonBody, null, ScanTextResponse.class);
    }

    /**
     * A convenience method that abstracts the details of the multi-step file upload and scan process. In other words,
     * calling this method for a given file is equivalent to (1) manually initializing a file upload session,
     * (2) uploading all chunks of the file, (3) completing the upload, and (4) triggering a scan of the file.
     *
     * <p>The maximum allowed <code>contentSizeBytes</code> is dependent on the terms of your current
     * Nightfall usage plan agreement; check the Nightfall dashboard for more details.
     *
     * <p>This method consumes the provided <code>InputStream</code>, but it *does not* close it; closing remains
     * the caller's responsibility.
     *
     * @param request contains configuration describing which detectors to use to scan the file, as well as a webhook
     *                URL for delivering the results of the scan.
     * @param content a stream of the bytes representing the file to upload
     * @param contentSizeBytes the size of the input stream
     * @return an acknowledgment that the asynchronous scan has been initiated.
     * @throws NightfallAPIException thrown if a non-2xx status code is returned by the API.
     * @throws NightfallClientException thrown if a I/O error occurs while processing the request
     * @throws NightfallRequestTimeoutException thrown if the request is aborted because read/write timeout is exceeded
     */
    public ScanFileResponse scanFile(ScanFileRequest request, InputStream content, long contentSizeBytes) {
        return scanFile(request, content, contentSizeBytes, null);
    }

    /**
     * A convenience method that abstracts the details of the multi-step file upload and scan process. In other words,
     * calling this method for a given file is equivalent to (1) manually initializing a file upload session,
     * (2) uploading all chunks of the file, (3) completing the upload, and (4) triggering a scan of the file.
     *
     * <p>The maximum allowed <code>contentSizeBytes</code> is dependent on the terms of your current
     * Nightfall usage plan agreement; check the Nightfall dashboard for more details.
     *
     * <p>This method consumes the provided <code>InputStream</code>, but it *does not* close it; closing remains
     * the caller's responsibility.
     *
     * @param request contains configuration describing which detectors to use to scan the file, as well as a webhook
     *                URL for delivering the results of the scan.
     * @param content a stream of the bytes representing the file to upload
     * @param contentSizeBytes the size of the input stream
     * @param timeout the allowed duration for the request; if the execution time exceeds this duration, the request
     *                will be aborted.
     * @return an acknowledgment that the asynchronous scan has been initiated.
     * @throws NightfallAPIException thrown if a non-2xx status code is returned by the API.
     * @throws NightfallClientException thrown if a I/O error occurs while processing the request
     * @throws NightfallRequestTimeoutException thrown if execution time exceeds the provided <code>timeout</code>,
     *      or if an HTTP request is terminated by the client for exceeding read/write timeouts.
     */
    public ScanFileResponse scanFile(
            ScanFileRequest request, InputStream content, long contentSizeBytes, Duration timeout) {
        if (request == null) {
            throw new IllegalArgumentException("request must be non-null");
        } else if (content == null) {
            throw new IllegalArgumentException("content must be non-null");
        }

        Instant deadline = null;
        if (timeout != null) {
            if (timeout.isNegative()) {
                throw new IllegalArgumentException("timeout must be positive");
            }

            deadline = Instant.now().plus(timeout);
        }

        InitializeFileUploadRequest initRequest = new InitializeFileUploadRequest(contentSizeBytes);
        FileUpload upload = this.initializeFileUpload(initRequest);

        AtomicReference<BaseNightfallException> uploadException = new AtomicReference<>();
        boolean uploadSuccess = doChunkedUpload(upload, content, deadline, uploadException);
        if (!uploadSuccess) {
            BaseNightfallException except = uploadException.get();
            if (except != null) {
                throw except;
            }
            throw new NightfallClientException("internal error: failed to upload all chunks of file");
        }

        CompleteFileUploadRequest completeReq = new CompleteFileUploadRequest(upload.getFileID());
        upload = this.completeFileUpload(completeReq);

        return this.scanUploadedFile(request, upload.getFileID());
    }

    private boolean doChunkedUpload(
            FileUpload upload, InputStream content, Instant deadline,
            AtomicReference<BaseNightfallException> uploadException) {
        // Use a semaphore to avoid loading the entire stream into memory
        int numPermits = this.fileUploadConcurrency;
        Semaphore semaphore = new Semaphore(numPermits);

        AtomicBoolean allChunksSucceed = new AtomicBoolean(true);
        for (int offset = 0; offset < upload.getFileSizeBytes(); offset += upload.getChunkSize()) {
            semaphore.acquireUninterruptibly();
            checkFileUploadDeadline(deadline);

            if (!allChunksSucceed.get()) {
                return false;
            }

            UploadFileChunkRequest chunkReq = new UploadFileChunkRequest(upload.getFileID(), offset);
            byte[] data = new byte[(int) upload.getChunkSize()];
            try {
                int bytesRead = content.read(data);
                boolean notLastChunk = offset + upload.getChunkSize() < upload.getFileSizeBytes();
                if (bytesRead < data.length && notLastChunk) {
                    semaphore.release();
                    throw new NightfallClientException("failed to read data from input stream");
                } else if (bytesRead < data.length) {
                    data = Arrays.copyOfRange(data, 0, bytesRead);
                }
            } catch (IOException e) {
                semaphore.release();
                throw new NightfallClientException("reading content to upload: " + e.getMessage());
            }
            chunkReq.setContent(data);

            this.executor.execute(() -> {
                try {
                    this.uploadFileChunk(chunkReq);
                } catch (BaseNightfallException e) {
                    allChunksSucceed.set(false);
                    uploadException.set(e);
                } catch (Throwable t) {
                    allChunksSucceed.set(false);
                } finally {
                    semaphore.release();
                }
            });
        }

        while (true) {
            try {
                // Attempt to acquire all permits; this is only possible when all chunks have been uploaded.
                // Allow spurious wake-ups in case the caller puts a deadline on the operation.
                boolean success = semaphore.tryAcquire(numPermits, wakeupDurationMillis, TimeUnit.MILLISECONDS);
                if (success) {
                    return allChunksSucceed.get();
                }
                checkFileUploadDeadline(deadline);
            } catch (InterruptedException e) {
                throw new NightfallClientException("interrupted while waiting for upload to complete");
            }
        }
    }

    private void checkFileUploadDeadline(Instant deadline) {
        if (deadline != null && Instant.now().isAfter(deadline)) {
            throw new NightfallRequestTimeoutException("timed out while uploading file");
        }
    }

    /**
     * Creates a file upload session. If this operation returns successfully, the ID returned as part of the
     * response object shall be used to refer to the file in all subsequent upload and scanning operations.
     *
     * @param request contains metadata describing the requested file upload, such as the file size in bytes.
     * @return an object representing the file upload.
     * @throws NightfallAPIException thrown if a non-2xx status code is returned by the API.
     * @throws NightfallClientException thrown if a I/O error occurs while processing the request
     * @throws NightfallRequestTimeoutException thrown if the request is aborted because read/write timeout is exceeded
     */
    private FileUpload initializeFileUpload(InitializeFileUploadRequest request) {
        byte[] jsonBody;
        try {
            jsonBody = objectMapper.writeValueAsBytes(request);
        } catch (JsonProcessingException e) {
            throw new NightfallClientException("processing init-upload request: " + e.getMessage());
        }

        MediaType json = MediaType.parse("application/json");
        return this.issueRequest("/v3/upload", "POST", json, jsonBody, null, FileUpload.class);
    }

    /**
     * Uploads the bytes stored at the provided offset of a file. The byte offset provided should be an exact
     * multiple of the <code>chunkSize</code> returned by the response when the upload session was created. The
     * number of bytes provided in the request should exactly match <code>chunkSize</code>, except if this chunk is
     * the last chunk of the file; then it may be less.
     *
     * @param request the data to upload, as well as metadata such as the offset at which to upload.
     * @return true if the chunk was uploaded
     * @throws NightfallAPIException thrown if a non-2xx status code is returned by the API.
     * @throws NightfallClientException thrown if a I/O error occurs while processing the request
     * @throws NightfallRequestTimeoutException thrown if the request is aborted because read/write timeout is exceeded
     */
    private boolean uploadFileChunk(UploadFileChunkRequest request) {
        Headers headers = Headers.of("X-Upload-Offset", Long.toString(request.getFileOffset()));
        String path = "/v3/upload/" + request.getFileUploadID().toString();
        MediaType octetStream = MediaType.parse("application/octet-stream");
        this.issueRequest(path, "PATCH", octetStream, request.getContent(), headers, Void.class);
        return true;
    }

    /**
     * Marks the file upload as complete, and coalesces all chunks into a single logical file. This method also
     * validates the uploaded bytes to make sure that they represent a file type for which Nightfall supports scans.
     *
     * @param request contains metadata identifying the file upload, namely the upload ID.
     * @return an object representing the file upload.
     * @throws NightfallAPIException thrown if a non-2xx status code is returned by the API.
     * @throws NightfallClientException thrown if a I/O error occurs while processing the request
     * @throws NightfallRequestTimeoutException thrown if the request is aborted because read/write timeout is exceeded
     */
    private FileUpload completeFileUpload(CompleteFileUploadRequest request) {
        String path = "/v3/upload/" + request.getFileUploadID().toString() + "/finish";
        MediaType json = MediaType.parse("application/json");
        return this.issueRequest(path, "POST", json, new byte[0], null, FileUpload.class);
    }

    /**
     * Triggers a scan of the file identified by the provided <code>fileID</code>. As the underlying file might be
     * arbitrarily large, this scan will be conducted asynchronously. Results from the scan will be delivered to the
     * webhook URL provided in the <code>request</code> payload.
     *
     * @param request contains metadata identifying which file to scan, as well as the configuration that
     *                describes which detectors to use when scanning.
     * @return an acknowledgment that the asynchronous scan has been initiated.
     * @throws NightfallAPIException thrown if a non-2xx status code is returned by the API.
     * @throws NightfallClientException thrown if a I/O error occurs while processing the request
     * @throws NightfallRequestTimeoutException thrown if the request is aborted because read/write timeout is exceeded
     */
    private ScanFileResponse scanUploadedFile(ScanFileRequest request, UUID fileID) {
        String path = "/v3/upload/" + fileID.toString() + "/scan";
        byte[] jsonBody;
        try {
            jsonBody = objectMapper.writeValueAsBytes(request);
        } catch (JsonProcessingException e) {
            throw new NightfallClientException("processing scan file request: " + e.getMessage());
        }

        MediaType json = MediaType.parse("application/json");
        return this.issueRequest(path, "POST", json, jsonBody, null, ScanFileResponse.class);
    }

    /**
     * Issues an HTTP request to the provided resource. If the request is successful, the response body will be
     * deserialized into an object based on the provided <code>responseClass</code>. If the response indicates a
     * rate limiting error, the request will be retried after a short sleep.
     *
     * @param path the HTTP resource path
     * @param method the HTTP verb
     * @param body the HTTP request body
     * @param headers HTTP headers
     * @param responseClass the class to deserialize results into
     * @return an instance of the <code>responseClass</code>
     * @throws NightfallClientException thrown if an unexpected error occurs while processing the request
     * @throws NightfallAPIException thrown if the API returns a 4xx or 5xx error code
     * @throws NightfallRequestTimeoutException thrown if the request is aborted because read/write timeout is exceeded
     */
    private <E> E issueRequest(
            String path, String method, MediaType mediaType, byte[] body, Headers headers, Class<E> responseClass) {
        String url = this.apiHost + path;
        Request.Builder builder = new Request.Builder().url(url);

        if (headers != null) {
            builder.headers(headers);
        }
        if (this.implVersion != null && !this.implVersion.equals("")) {
            builder.addHeader("User-Agent", "nightfall-java-sdk/" + this.implVersion);
        }

        builder.addHeader("Authorization", "Bearer " + this.apiKey);

        RequestBody reqBody = null;
        if (body != null && body.length > 0) {
            reqBody = RequestBody.create(body, mediaType);
        } else if (!method.equals("GET") && !method.equals("HEAD")) {
            reqBody = RequestBody.create(new byte[0]);
        }

        builder.method(method, reqBody);

        Request request = builder.build();
        Call call = this.httpClient.newCall(request);

        NightfallErrorResponse lastError = null;
        int errorCode = 0;
        for (int attempt = 0; attempt < this.retryCount; attempt++) {
            try (Response response = call.execute()) {
                if (!response.isSuccessful()) {
                    try {
                        lastError = objectMapper.readValue(response.body().bytes(), NightfallErrorResponse.class);
                    } catch (Throwable t) {
                        // best effort to get more info, swallow failure
                    }

                    if (response.code() == 429 && attempt < this.retryCount - 1) {
                        Thread.sleep(1000);
                        call = call.clone(); // cannot re-use the same call object
                        continue;
                    }

                    // cannot directly throw exception here because of Throwable catch branch; need to break
                    errorCode = response.code();
                    break;
                }

                if (Void.class.equals(responseClass)) {
                    return null;
                }
                return objectMapper.readValue(response.body().bytes(), responseClass);
            } catch (IOException e) {
                // If OkHTTP times out, allow retries
                if (e.getMessage().equalsIgnoreCase("timeout") || e.getMessage().equalsIgnoreCase("read timed out")) {
                    if (attempt >= this.retryCount - 1) {
                        throw new NightfallRequestTimeoutException("request timed out");
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ee) {
                        // swallow
                    }
                    call = call.clone(); // cannot re-use the same call object
                    continue;
                }
                throw new NightfallClientException("issuing HTTP request: " + e.getMessage());
            } catch (Throwable t) {
                throw new NightfallClientException("failure executing HTTP request: " + t.getMessage());
            }
        }

        if (errorCode > 0) {
            throw new NightfallAPIException("unsuccessful response", lastError, errorCode);
        }

        String message = "exceeded max retry count on request: " + path;
        throw new NightfallAPIException(message, lastError, 429);
    }

    /**
     * A builder class that configures, validates, then creates instances of a Nightfall Client.
     */
    public static class Builder {
        private String apiKey;
        private int fileUploadConcurrency = 1;

        private Duration connectionTimeout = Duration.ofSeconds(10);
        private Duration readTimeout = Duration.ofSeconds(30);
        private Duration writeTimeout = Duration.ofSeconds(60);
        private int maxIdleConnections = 100;
        private Duration keepAliveDuration = Duration.ofSeconds(30);

        /**
         * Builds and returns the client with all default values. The API key is loaded from the environment variable
         * <code>NIGHTFALL_API_KEY</code>. The underlying client manages an HTTP connection pool, so instantiating
         * more than one Nightfall client is not necessary.
         *
         * @return a Nightfall client
         * @throws IllegalArgumentException if no value is set for the API key
         */
        public static NightfallClient defaultClient() {
            ConnectionPool cxnPool = new ConnectionPool(100, 30, TimeUnit.SECONDS);
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .readTimeout(Duration.ofSeconds(30))
                    .writeTimeout(Duration.ofSeconds(60))
                    .connectionPool(cxnPool)
                    .build();
            return new NightfallClient(API_HOST, readAPIKeyFromEnvironment(), 1, httpClient);
        }

        /**
         * Sets the API key for the Nightfall Client.
         *
         * @param apiKey a valid Nightfall API key
         * @return the builder
         */
        public Builder withAPIKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        /**
         * Sets the concurrency for file upload operations. This field represents the number of HTTP requests that
         * may execute in parallel when uploading file bytes. Be cognizant of your HTTP connection pool settings
         * when deciding on a value in order to optimize your upload bandwidth.
         * Valid values are in the range [1, 100], inclusive. Defaults to 1 if unset.
         *
         * @param concurrency an integer in the range [1, 100]
         * @return the builder
         * @throws IllegalArgumentException if the argument falls outside the allowed range
         */
        public Builder withFileUploadConcurrency(int concurrency) {
            if (concurrency <= 0 || concurrency > 100) {
                throw new IllegalArgumentException("fileUploadConcurrency must be in range [1,100]");
            }
            this.fileUploadConcurrency = concurrency;
            return this;
        }

        /**
         * Sets the connection timeout for the underlying HTTP client. If unset, defaults to 10 seconds. If set
         * to 0, connections will not time out.
         *
         * @param connectionTimeout a non-negative duration less than or equal to 60 seconds
         * @return the builder
         * @throws IllegalArgumentException if the argument falls outside the allowed range
         */
        public Builder withConnectionTimeout(Duration connectionTimeout) {
            if (connectionTimeout == null || connectionTimeout.isNegative() || connectionTimeout.getSeconds() > 60) {
                throw new IllegalArgumentException("connectionTimeout must be a non-negative duration <= 60 seconds");
            }
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        /**
         * Sets the read timeout for the underlying HTTP client. If unset, defaults to 30 seconds. If set
         * to 0, reads will not time out.
         *
         * @param readTimeout a non-negative duration less than or equal to 120 seconds
         * @return the builder
         * @throws IllegalArgumentException if the argument falls outside the allowed range
         */
        public Builder withReadTimeout(Duration readTimeout) {
            if (readTimeout == null || readTimeout.isNegative() || readTimeout.getSeconds() > 120) {
                throw new IllegalArgumentException("readTimeout must be a non-negative duration <= 120 seconds");
            }
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * Sets the write timeout for the underlying HTTP client. If unset, defaults to 60 seconds. If set
         * to 0, writes will not time out.
         *
         * @param writeTimeout a non-negative duration less than or equal to 120 seconds
         * @return the builder
         * @throws IllegalArgumentException if the argument falls outside the allowed range
         */
        public Builder withWriteTimeout(Duration writeTimeout) {
            if (writeTimeout == null || writeTimeout.isNegative() || writeTimeout.getSeconds() > 120) {
                throw new IllegalArgumentException("writeTimeout must be a non-negative duration <= 120 seconds");
            }
            this.writeTimeout = writeTimeout;
            return this;
        }

        /**
         * Sets the maximum number of idle connections in the underlying HTTP client. Be sure this value cooperates with
         * the configuration for <code>fileUploadConcurrency</code>. If unset, defaults to 100.
         *
         * @param maxIdleConnections an integer in the range [1, 500]
         * @return the builder
         * @throws IllegalArgumentException if the argument falls outside the allowed range
         */
        public Builder withMaxIdleConnections(int maxIdleConnections) {
            if (maxIdleConnections < 1 || maxIdleConnections > 500) {
                throw new IllegalArgumentException("maxIdleConnections must be in the range [1, 500]");
            }
            this.maxIdleConnections = maxIdleConnections;
            return this;
        }

        /**
         * Sets the keep-alive duration for a connection in the underlying HTTP connection pool. If unset,
         * defaults to 30 seconds.
         *
         * @param keepAliveDuration a positive duration less than or equal to 120 seconds
         * @return the builder
         * @throws IllegalArgumentException if the argument falls outside the allowed range
         */
        public Builder withKeepAliveDuration(Duration keepAliveDuration) {
            if (keepAliveDuration == null || keepAliveDuration.isNegative() || keepAliveDuration.isZero()
                    || keepAliveDuration.getSeconds() > 120) {
                throw new IllegalArgumentException("keepAliveDuration must be a positive duration <= 120 seconds");
            }
            this.keepAliveDuration = keepAliveDuration;
            return this;
        }

        /**
         * Builds the client using the configured values, falling back on defaults if any values
         * were not explicitly set.
         *
         * @return a Nightfall client
         * @throws IllegalArgumentException if the API key was not set
         */
        public NightfallClient build() {
            if (this.apiKey == null || this.apiKey.equals("")) {
                this.apiKey = readAPIKeyFromEnvironment();
            }

            ConnectionPool cxnPool = new ConnectionPool(this.maxIdleConnections,
                    this.keepAliveDuration.toMillis(), TimeUnit.MILLISECONDS);
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(this.connectionTimeout)
                    .readTimeout(this.readTimeout)
                    .writeTimeout(this.writeTimeout)
                    .connectionPool(cxnPool)
                    .build();
            return new NightfallClient(API_HOST, this.apiKey, this.fileUploadConcurrency, httpClient);
        }

        private static String readAPIKeyFromEnvironment() {
            String apiKey = System.getenv("NIGHTFALL_API_KEY");
            if (apiKey == null || apiKey.equals("")) {
                throw new IllegalArgumentException("Missing value for NIGHTFALL_API_KEY environment variable");
            }
            return apiKey;
        }
    }
}
