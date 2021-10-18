package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class Finding {
    @JsonProperty("finding")
    private String finding;

    @JsonProperty("beforeContext")
    private String beforeContext;

    @JsonProperty("afterContext")
    private String afterContext;

    @JsonProperty("detector")
    private DetectorMetadata detector;

    @JsonProperty("confidence")
    private String confidence;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("matchedDetectionRuleUUIDs")
    private List<UUID> matchedDetectionRuleUUIDs;

    @JsonProperty("matchedDetectionRules")
    private List<String> matchedDetectionRules;

    public String getFinding() {
        return finding;
    }

    public String getBeforeContext() {
        return beforeContext;
    }

    public String getAfterContext() {
        return afterContext;
    }

    public DetectorMetadata getDetector() {
        return detector;
    }

    public String getConfidence() {
        return confidence;
    }

    public Location getLocation() {
        return location;
    }

    public List<UUID> getMatchedDetectionRuleUUIDs() {
        return matchedDetectionRuleUUIDs;
    }

    public List<String> getMatchedDetectionRules() {
        return matchedDetectionRules;
    }

    @Override
    public String toString() {
        return "Finding{" +
                "finding='" + finding + '\'' +
                ", beforeContext='" + beforeContext + '\'' +
                ", afterContext='" + afterContext + '\'' +
                ", detector=" + detector +
                ", confidence='" + confidence + '\'' +
                ", location=" + location +
                ", matchedDetectionRuleUUIDs=" + matchedDetectionRuleUUIDs +
                ", matchedDetectionRules=" + matchedDetectionRules +
                '}';
    }
}
