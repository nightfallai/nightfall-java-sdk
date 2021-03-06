package ai.nightfall.scan.model;

import java.util.UUID;

/**
 * The container object containing all parameters required to complete a file upload.
 */
public class CompleteFileUploadRequest {

    private UUID fileUploadID;

    /**
     * Constructs a new request to complete a file upload.
     *
     * @param fileUploadID the file ID
     */
    public CompleteFileUploadRequest(UUID fileUploadID) {
        this.fileUploadID = fileUploadID;
    }

    /**
     * Returns the file ID.
     *
     * @return the file ID
     */
    public UUID getFileUploadID() {
        return fileUploadID;
    }

    /**
     * Sets the file ID.
     *
     * @param fileUploadID the file ID
     */
    public void setFileUploadID(UUID fileUploadID) {
        this.fileUploadID = fileUploadID;
    }
}
