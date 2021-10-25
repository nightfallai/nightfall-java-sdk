package ai.nightfall.scan.model;

import java.util.UUID;

/**
 * An object representing a request to upload a chunk of file data to the Nightfall API.
 */
public class UploadFileChunkRequest {
    private UUID fileUploadID;
    private long fileOffset;
    private byte[] content;

    /**
     * Create a new instance of a file chunk upload request.
     *
     * @param fileUploadID the ID of the file
     * @param fileOffset the offset at which to upload bytes
     */
    public UploadFileChunkRequest(UUID fileUploadID, long fileOffset) {
        this.fileUploadID = fileUploadID;
        this.fileOffset = fileOffset;
    }

    /**
     * Create a new instance of a file chunk upload request.
     *
     * @param fileUploadID the ID of the file
     * @param fileOffset the offset at which to upload bytes
     * @param content the payload bytes to upload
     */
    public UploadFileChunkRequest(UUID fileUploadID, long fileOffset, byte[] content) {
        this.fileUploadID = fileUploadID;
        this.fileOffset = fileOffset;
        this.content = content;
    }

    /**
     * Get the file ID.
     *
     * @return the file ID
     */
    public UUID getFileUploadID() {
        return fileUploadID;
    }

    /**
     * Get the file offset.
     *
     * @return the offset at which to upload bytes
     */
    public long getFileOffset() {
        return fileOffset;
    }

    /**
     * Get the request payload.
     *
     * @return the payload bytes to upload
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Set the request payload.
     *
     * @param content the content to upload
     */
    public void setContent(byte[] content) {
        this.content = content;
    }
}
