package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class FileUpload {
    @JsonProperty("id")
    private UUID fileID;

    @JsonProperty("fileSizeBytes")
    private long fileSizeBytes;

    @JsonProperty("chunkSize")
    private long chunkSize;

    @JsonProperty("mimeType")
    private String mimeType;

    public UUID getFileID() {
        return fileID;
    }

    public long getFileSizeBytes() {
        return fileSizeBytes;
    }

    public long getChunkSize() {
        return chunkSize;
    }

    public String getMimeType() {
        return mimeType;
    }
}
