package ai.nightfall.scan.model;

import java.util.UUID;

public class UploadFileChunkRequest {
    private UUID fileUploadID;
    private long fileOffset;
    private byte[] content;

    public UploadFileChunkRequest(UUID fileUploadID, long fileOffset) {
        this.fileUploadID = fileUploadID;
        this.fileOffset = fileOffset;
    }

    public UploadFileChunkRequest(UUID fileUploadID, long fileOffset, byte[] content) {
        this.fileUploadID = fileUploadID;
        this.fileOffset = fileOffset;
        this.content = content;
    }

    public UUID getFileUploadID() {
        return fileUploadID;
    }

    public void setFileUploadID(UUID fileUploadID) {
        this.fileUploadID = fileUploadID;
    }

    public long getFileOffset() {
        return fileOffset;
    }

    public void setFileOffset(long fileOffset) {
        this.fileOffset = fileOffset;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
