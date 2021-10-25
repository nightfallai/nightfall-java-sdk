package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object representing a request to upload a new file to Nightfall.
 */
public class InitializeFileUploadRequest {
    @JsonProperty("fileSizeBytes")
    private long fileSizeBytes;

    /**
     * Create a new request to upload a file to Nightfall.
     *
     * @param fileSizeBytes the size of the file in bytes
     */
    public InitializeFileUploadRequest(long fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
    }

    /**
     * Get the file size.
     *
     * @return the size of the file in bytes
     */
    public long getFileSizeBytes() {
        return fileSizeBytes;
    }
}
