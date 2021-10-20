package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * The error model returned by Nightfall API requests that are unsuccessful. This object is generally returned
 * when the HTTP status code is outside the range 200-299.
 */
public class NightfallErrorResponse {

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("description")
    private String description;

    @JsonProperty("additionalData")
    private Map<String, String> additionalData;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getAdditionalData() {
        return additionalData;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                ", additionalData=" + additionalData +
                '}';
    }
}
