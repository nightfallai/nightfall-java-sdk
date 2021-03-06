package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * A container for minimal information representing a detector. A detector may be uniquely identified by its UUID;
 * the name field helps provide human-readability.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetectorMetadata {

    @JsonProperty("name")
    private String name;

    @JsonProperty("uuid")
    private UUID uuid;

    /**
     * Get the detector name.
     *
     * @return the name of the detector
     */
    public String getName() {
        return name;
    }

    /**
     * Get the detector UUID.
     *
     * @return the ID that uniquely identifies this detector
     */
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "DetectorMetadata{"
                + "name='" + name + '\''
                + ", uuid=" + uuid
                + '}';
    }
}
