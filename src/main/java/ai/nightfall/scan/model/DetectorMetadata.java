package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DetectorMetadata {

    @JsonProperty("name")
    private String name;

    @JsonProperty("uuid")
    private UUID uuid;

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "DetectorMetadata{" +
                "name='" + name + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}
