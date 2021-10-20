package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ScanFileResponse {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("message")
    private String message;

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
