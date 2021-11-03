package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;

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

    // appease jackson serialization
    public NightfallErrorResponse() {}

    /**
     * Builds a new NightfallErrorResponse object.
     *
     * @param code the error code
     * @param message the error message
     * @param description further description
     * @param additionalData a map of key-value pairs that contain even further debugging information
     */
    public NightfallErrorResponse(int code, String message, String description, Map<String, String> additionalData) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.additionalData = additionalData;
    }

    /**
     * Get the error code.
     *
     * @return the error code returned by the API
     */
    public int getCode() {
        return code;
    }

    /**
     * Get the error message.
     *
     * @return the error message returned by the API
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the error description.
     *
     * @return additional details describing the circumstance surrounding the error
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get supplemental error data.
     *
     * @return supplemental data that may be useful in debugging the error message
     */
    public Map<String, String> getAdditionalData() {
        return additionalData;
    }

    @Override
    public String toString() {
        return "Error{"
                + "code=" + code
                + ", message='" + message + '\''
                + ", description='" + description + '\''
                + ", additionalData=" + additionalData
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NightfallErrorResponse that = (NightfallErrorResponse) o;
        return code == that.code && Objects.equals(message, that.message)
                && Objects.equals(description, that.description)
                && Objects.equals(additionalData, that.additionalData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, description, additionalData);
    }
}
