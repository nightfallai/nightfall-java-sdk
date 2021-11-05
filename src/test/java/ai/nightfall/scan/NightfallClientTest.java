package ai.nightfall.scan;

import ai.nightfall.scan.model.Finding;
import ai.nightfall.scan.model.NightfallAPIException;
import ai.nightfall.scan.model.NightfallErrorResponse;
import ai.nightfall.scan.model.NightfallRequestTimeoutException;
import ai.nightfall.scan.model.ScanFileRequest;
import ai.nightfall.scan.model.ScanFileResponse;
import ai.nightfall.scan.model.ScanPolicy;
import ai.nightfall.scan.model.ScanTextRequest;
import ai.nightfall.scan.model.ScanTextResponse;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okhttp3.mockwebserver.SocketPolicy;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the Nightfall Client.
 */
@EnableRuleMigrationSupport
public class NightfallClientTest {

    @Test
    public void testScanText_HappyPath() {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody("{\"findings\": [[]]}"));

            List<List<Finding>> expectedFindings = Arrays.asList(Arrays.asList());
            NightfallClient c = new NightfallClient(getRequestURL(server), "key", 1, getHttpClient());
            ScanTextRequest req = new ScanTextRequest(null, null);
            ScanTextResponse resp = c.scanText(req);
            assertEquals(expectedFindings, resp.getFindings());
        } catch (IOException e) {
            fail("IOException during test: " + e.getMessage());
        }
    }

    @Test
    public void testScanText_NullRequestArg() {
        assertThrows(IllegalArgumentException.class, () -> {
            NightfallClient client = new NightfallClient("host", "key", 1, new OkHttpClient());
            client.scanText(null);
        });
    }

    @Test
    public void testScanText_IOTimeout() {
        assertThrows(NightfallRequestTimeoutException.class, () -> {
            try (MockWebServer server = new MockWebServer()) {
                server.setDispatcher(new Dispatcher() {
                    @NotNull
                    @Override
                    public MockResponse dispatch(@NotNull RecordedRequest r) {
                        // time out on all requests
                        return new MockResponse().setSocketPolicy(SocketPolicy.NO_RESPONSE);
                    }
                });

                NightfallClient c = new NightfallClient(getRequestURL(server), "key", 1, getHttpClient());
                ScanTextRequest req = new ScanTextRequest(null, null);
                c.scanText(req);
                fail("did not expect scanText to succeed");
            } catch (IOException e) {
                fail("IOException during test: " + e.getMessage());
            }
        });
    }

    @Test
    public void testScanText_RateLimitRetried() {
        // use reference type to work around Java inner class limitations
        final int[] reqCount = {0};

        try (MockWebServer server = new MockWebServer()) {
            server.setDispatcher(new Dispatcher() {
                @NotNull
                @Override
                public MockResponse dispatch(@NotNull RecordedRequest r) {
                    if (++reqCount[0] >= 3) {
                        return new MockResponse().setBody("{\"findings\": [[]]}");
                    }
                    // rate limit the first few requests
                    return new MockResponse().setResponseCode(429).setBody(getRateLimitErrorResponse());
                }
            });

            List<List<Finding>> expectedFindings = Arrays.asList(Arrays.asList());
            NightfallClient c = new NightfallClient(getRequestURL(server), "key", 1, getHttpClient());
            ScanTextRequest req = new ScanTextRequest(null, null);
            ScanTextResponse resp = c.scanText(req);
            assertEquals(expectedFindings, resp.getFindings());
            assertEquals(reqCount[0], 3); // validate that rate limit responses were actually returned
        } catch (IOException e) {
            fail("IOException during test: " + e.getMessage());
        }
    }

    @Test
    public void testScanText_InternalException() {
        NightfallErrorResponse expectedErr = new NightfallErrorResponse(500, "internal", "internal error", null);
        NightfallAPIException expectedException = new NightfallAPIException("unsuccessful response", expectedErr, 500);

        NightfallAPIException actualException = assertThrows(NightfallAPIException.class, () -> {
            try (MockWebServer server = new MockWebServer()) {
                String body = "{\"code\": 500, \"message\": \"internal\", \"description\": \"internal error\"}";
                server.enqueue(new MockResponse().setResponseCode(500).setBody(body));

                String serverURL = new HttpUrl.Builder()
                        .scheme("http")
                        .host(server.getHostName())
                        .port(server.getPort())
                        .build().url().toString();

                NightfallClient c = new NightfallClient(serverURL, "key", 1, getHttpClient());
                ScanTextRequest req = new ScanTextRequest(null, null);
                c.scanText(req);
                fail("did not expect scanText to succeed");
            } catch (IOException e) {
                fail("IOException during test: " + e.getMessage());
            }
        });

        assertEquals(expectedException.getError(), actualException.getError());
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testScanText_429RetryCountExceeded() {
        NightfallErrorResponse expectedErr = new NightfallErrorResponse(
                429, "too many requests", "too many requests", null);
        NightfallAPIException expectedException = new NightfallAPIException(
                "exceeded max retry count on request: /v3/scan", expectedErr, 429);

        NightfallAPIException actualException = assertThrows(NightfallAPIException.class, () -> {
            try (MockWebServer server = new MockWebServer()) {
                String body =
                        "{\"code\": 429, \"message\": \"too many requests\", \"description\": \"too many requests\"}";
                server.setDispatcher(new Dispatcher() {
                    @NotNull
                    @Override
                    public MockResponse dispatch(@NotNull RecordedRequest r) {
                        return new MockResponse().setResponseCode(429).setBody(body);
                    }
                });

                String serverURL = new HttpUrl.Builder()
                        .scheme("http")
                        .host(server.getHostName())
                        .port(server.getPort())
                        .build().url().toString();

                NightfallClient c = new NightfallClient(serverURL, "key", 1, getHttpClient());
                ScanTextRequest req = new ScanTextRequest(null, null);
                c.scanText(req);
                fail("did not expect scanText to succeed");
            } catch (IOException e) {
                fail("IOException during test: " + e.getMessage());
            }
        });

        assertEquals(expectedException.getError(), actualException.getError());
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testScanFile_InvalidReq() {
        assertThrows(IllegalArgumentException.class, () -> {
            NightfallClient c = new NightfallClient("url", "key", 1, getHttpClient());
            c.scanFile(null, new ByteArrayInputStream(new byte[0]), 0, null);
            fail("should not have completed call");
        });
    }

    @Test
    public void testScanFile_InvalidContent() {
        assertThrows(IllegalArgumentException.class, () -> {
            NightfallClient c = new NightfallClient("url", "key", 1, getHttpClient());
            ScanFileRequest req = new ScanFileRequest(UUID.randomUUID(), "");
            c.scanFile(req, null, 0, null);
            fail("should not have completed call");
        });
    }

    @Test
    public void testScanFile_InvalidDuration() {
        assertThrows(IllegalArgumentException.class, () -> {
            NightfallClient c = new NightfallClient("url", "key", 1, getHttpClient());
            ScanFileRequest req = new ScanFileRequest(UUID.randomUUID(), "");
            c.scanFile(req, new ByteArrayInputStream(new byte[0]), 0, Duration.ofSeconds(-4));
            fail("should not have completed call");
        });
    }

    @Test
    public void testScanFile_HappyPath() {
        try (MockWebServer server = new MockWebServer()) {
            server.enqueue(new MockResponse().setBody("{\"id\": \"2eda1019-f991-4535-be9f-cecbe6b6c2eb\","
                    + "\"fileSizeBytes\": 1738, \"mimeType\": \"text/plain\", \"chunkSize\": 10485760}"));
            server.enqueue(new MockResponse().setResponseCode(204));
            server.enqueue(new MockResponse().setBody("{\"id\": \"2eda1019-f991-4535-be9f-cecbe6b6c2eb\","
                    + "\"fileSizeBytes\": 1738, \"mimeType\": \"text/plain\", \"chunkSize\": 10485760}"));
            server.enqueue(new MockResponse().setBody("{\"id\": \"2eda1019-f991-4535-be9f-cecbe6b6c2eb\","
                    + "\"message\": \"scan initiated\"}"));

            ScanFileResponse expectedResponse = new ScanFileResponse(
                    UUID.fromString("2eda1019-f991-4535-be9f-cecbe6b6c2eb"), "scan initiated");
            NightfallClient c = new NightfallClient(getRequestURL(server), "key", 1, getHttpClient());
            ScanFileRequest req = new ScanFileRequest(new ScanPolicy("foo", null, null), "foo");
            ScanFileResponse resp = c.scanFile(req, new ByteArrayInputStream(new byte[1738]), 1738, null);

            assertEquals(expectedResponse.getId(), resp.getId());
            assertEquals(expectedResponse.getMessage(), resp.getMessage());
        } catch (IOException e) {
            fail("IOException during test: " + e.getMessage());
        }
    }

    @Test
    public void testScanFile_Timeout() {
        assertThrows(NightfallRequestTimeoutException.class, () -> {
            try (MockWebServer server = new MockWebServer()) {
                server.enqueue(new MockResponse().setBody("{\"id\": \"2eda1019-f991-4535-be9f-cecbe6b6c2eb\","
                        + "\"fileSizeBytes\": 1738, \"mimeType\": \"text/plain\", \"chunkSize\": 10485760}"));
                server.enqueue(new MockResponse().setResponseCode(204));
                server.enqueue(new MockResponse().setBody("{\"id\": \"2eda1019-f991-4535-be9f-cecbe6b6c2eb\","
                        + "\"fileSizeBytes\": 1738, \"mimeType\": \"text/plain\", \"chunkSize\": 10485760}"));
                server.enqueue(new MockResponse().setBody("{\"id\": \"2eda1019-f991-4535-be9f-cecbe6b6c2eb\","
                        + "\"message\": \"scan initiated\"}"));

                NightfallClient c = new NightfallClient(getRequestURL(server), "key", 1, getHttpClient());
                ScanFileRequest req = new ScanFileRequest(new ScanPolicy("foo", null, null), "foo");
                Duration timeout = Duration.ofMillis(1);
                c.scanFile(req, new ByteArrayInputStream(new byte[1738]), 1738, timeout);
                fail("did not expect scan to succeed");
            } catch (IOException e) {
                fail("IOException during test: " + e.getMessage());
            }
        });
    }

    @Test
    public void testScanFile_APIException() {
        assertThrows(NightfallAPIException.class, () -> {
            // use reference type to work around Java inner class limitations
            final int[] reqCount = {0};

            try (MockWebServer server = new MockWebServer()) {
                final String successBody = "{\"id\": \"2eda1019-f991-4535-be9f-cecbe6b6c2eb\","
                        + "\"fileSizeBytes\": 1738, \"mimeType\": \"text/plain\", \"chunkSize\": 10485760}";
                final String errBody = "{\"code\": 400, \"message\": \"failure\", \"description\": \"failure\"}";
                server.setDispatcher(new Dispatcher() {
                    @NotNull
                    @Override
                    public MockResponse dispatch(@NotNull RecordedRequest r) {
                        if (++reqCount[0] == 1) {
                            return new MockResponse().setBody(successBody);
                        }

                        // fail chunk upload attempts
                        return new MockResponse().setResponseCode(400).setBody(errBody);
                    }
                });

                NightfallClient c = new NightfallClient(getRequestURL(server), "key", 1, getHttpClient());
                ScanFileRequest req = new ScanFileRequest(new ScanPolicy("foo", null, null), "foo");
                c.scanFile(req, new ByteArrayInputStream(new byte[1738]), 1738, null);
                fail("did not expect scan to succeed");
            } catch (IOException e) {
                fail("IOException during test: " + e.getMessage());
            }
        });
    }

    private OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(Duration.ofMillis(500))
                .writeTimeout(Duration.ofMillis(500))
                .connectTimeout(Duration.ofMillis(500))
                .build();
    }

    private String getRequestURL(MockWebServer server) {
        return new HttpUrl.Builder()
                .scheme("http")
                .host(server.getHostName())
                .port(server.getPort())
                .build().url().toString();
    }

    private String getRateLimitErrorResponse() {
        return "{\"code\": 429, \"message\": \"Too Many Requests\", \"description\": \"Try again later\"}";
    }
}
