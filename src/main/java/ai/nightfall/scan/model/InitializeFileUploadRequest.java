package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InitializeFileUploadRequest {
    @JsonProperty("fileSizeBytes")
    private long fileSizeBytes;

    public InitializeFileUploadRequest(long fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
    }

    public long getFileSizeBytes() {
        return fileSizeBytes;
    }

    public void setFileSizeBytes(long fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
    }
}
