package ai.nightfall.scan.model;

import java.util.UUID;

public class CompleteFileUploadRequest {

    private UUID fileUploadID;

    public CompleteFileUploadRequest(UUID fileUploadID) {
        this.fileUploadID = fileUploadID;
    }

    public UUID getFileUploadID() {
        return fileUploadID;
    }

    public void setFileUploadID(UUID fileUploadID) {
        this.fileUploadID = fileUploadID;
    }
}
