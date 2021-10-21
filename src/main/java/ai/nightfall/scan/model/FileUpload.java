package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * An object representing a file upload
 */
public class FileUpload {
    @JsonProperty("id")
    private UUID fileID;

    @JsonProperty("fileSizeBytes")
    private long fileSizeBytes;

    @JsonProperty("chunkSize")
    private long chunkSize;

    @JsonProperty("mimeType")
    private String mimeType;

    /**
     *
     * @return a unique ID representing this file
     */
    public UUID getFileID() {
        return fileID;
    }

    /**
     *
     * @return the size of the file in bytes
     */
    public long getFileSizeBytes() {
        return fileSizeBytes;
    }

    /**
     *
     * @return the number of bytes to use when uploading the file chunk-by-chunk
     */
    public long getChunkSize() {
        return chunkSize;
    }

    /**
     *
     * @return the RFC-2045 media type represented by this file
     */
    public String getMimeType() {
        return mimeType;
    }
}
