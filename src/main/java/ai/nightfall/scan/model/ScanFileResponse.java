package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * The object returned by the Nightfall API when an (asynchronous) file scan request was successfully triggered.
 */
public class ScanFileResponse {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("message")
    private String message;

    /**
     * Get the file ID.
     *
     * @return the ID of the file whose scan was triggered
     */
    public UUID getId() {
        return id;
    }

    /**
     * Get the status message.
     *
     * @return a status message describing the file scan
     */
    public String getMessage() {
        return message;
    }
}
