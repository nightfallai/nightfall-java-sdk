package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

/**
 * An object representing an occurrence of a configured detector (i.e. finding) in the provided data.
 */
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

    /**
     * Get the finding.
     *
     * @return the data that triggered a detector match
     */
    public String getFinding() {
        return finding;
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
     * @return the location where the data was in the original input file
     */
    public Location getLocation() {
        return location;
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
                + ", beforeContext='" + beforeContext + '\''
                + ", afterContext='" + afterContext + '\''
                + ", detector=" + detector
                + ", confidence='" + confidence + '\''
                + ", location=" + location
                + ", matchedDetectionRuleUUIDs=" + matchedDetectionRuleUUIDs
                + ", matchedDetectionRules=" + matchedDetectionRules
                + '}';
    }
}
