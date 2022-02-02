package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

/**
 * An object representing an occurrence of a configured detector (i.e. finding) in the provided data.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Finding {
    @JsonProperty("finding")
    private String finding;

    @JsonProperty("redactedFinding")
    private String redactedFinding;

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

    @JsonProperty("redactedLocation")
    private Location redactedLocation;

    @JsonProperty("matchedDetectionRuleUUIDs")
    private List<UUID> matchedDetectionRuleUUIDs;

    @JsonProperty("matchedDetectionRules")
    private List<String> matchedDetectionRules;

    /**
     * Get the finding.
     *
     * @return the data that triggered a detector match
     */
    public String getFinding() {
        return finding;
    }

    /**
     * Returns the redacted finding. This will only be non-empty if redaction configuration was provided
     * as part of the request payload.
     *
     * @return the redacted finding
     */
    public String getRedactedFinding() {
        return redactedFinding;
    }

    /**
     * Get the preceding context.
     *
     * @return the data that immediately preceded the finding
     */
    public String getBeforeContext() {
        return beforeContext;
    }

    /**
     * Get the trailing context.
     *
     * @return the data that immediately succeeded the finding
     */
    public String getAfterContext() {
        return afterContext;
    }

    /**
     * Get the detector.
     *
     * @return the detector that triggered the match
     */
    public DetectorMetadata getDetector() {
        return detector;
    }

    /**
     * Get the finding confidence.
     *
     * @return the confidence that the data contained in <code>finding</code> is an instance of the matched detector
     */
    public String getConfidence() {
        return confidence;
    }

    /**
     * Get the location of the finding.
     *
     * @return the location where the data was in the original content
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Get the location that the redacted finding would occupy in the original content if it were to replace
     * the finding.
     *
     * @return the location that the data occupies in its redacted form with regard to the original content
     */
    public Location getRedactedLocation() {
        return redactedLocation;
    }

    /**
     * Get the list of matched detection rule UUIDs.
     *
     * @return the list of detection rule UUIDs that contained a detector that triggered a match
     */
    public List<UUID> getMatchedDetectionRuleUUIDs() {
        return matchedDetectionRuleUUIDs;
    }

    /**
     * Get the list of matched detection rules.
     *
     * @return the list of inline detection rules that contained a detector that triggered a match
     */
    public List<String> getMatchedDetectionRules() {
        return matchedDetectionRules;
    }

    @Override
    public String toString() {
        return "Finding{"
                + "finding='" + finding + '\''
                + ", redactedFinding='" + redactedFinding + '\''
                + ", beforeContext='" + beforeContext + '\''
                + ", afterContext='" + afterContext + '\''
                + ", detector=" + detector
                + ", confidence='" + confidence + '\''
                + ", location=" + location
                + ", redactedLocation=" + redactedLocation
                + ", matchedDetectionRuleUUIDs=" + matchedDetectionRuleUUIDs
                + ", matchedDetectionRules=" + matchedDetectionRules
                + '}';
    }
}
