package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

/**
 * The configuration object to use when scanning inline plaintext with the Nightfall API.
 */
public class ScanTextConfig {

    @JsonProperty("detectionRuleUUIDs")
    private List<UUID> detectionRuleUUIDs;

    @JsonProperty("detectionRules")
    private List<DetectionRule> detectionRules;

    @JsonProperty("contextBytes")
    private int contextBytes;

    /**
     * Create a scan configuration with the provided detection rules.
     *
     * @param detectionRuleUUIDs a list of detection rules to use to scan content (maximum length 10)
     * @param contextBytes the number of bytes of context (leading and trailing) to return with any matched findings
     * @return the scan configuration
     */
    public static ScanTextConfig fromDetectionRuleUUIDs(List<UUID> detectionRuleUUIDs, int contextBytes) {
        return new ScanTextConfig(detectionRuleUUIDs, null, contextBytes);
    }

    /**
     * Create a scan configuration with the provided detection rules.
     *
     * @param detectionRules a list of detection rules to use to scan content (maximum length 10)
     * @param contextBytes the number of bytes of context (leading and trailing) to return with any matched findings
     * @return the scan configuration
     */
    public static ScanTextConfig fromDetectionRules(List<DetectionRule> detectionRules, int contextBytes) {
        return new ScanTextConfig(null, detectionRules, contextBytes);
    }

    /**
     * Create a scan configuration with the provided inline detection rules and detection rule UUIDs.
     *
     * @param detectionRuleUUIDs a list of detection rules to use to scan content (maximum length 10)
     * @param detectionRules a list of detection rules to use to scan content (maximum length 10)
     * @param contextBytes the number of bytes of context (leading and trailing) to return with any matched findings
     */
    public ScanTextConfig(List<UUID> detectionRuleUUIDs, List<DetectionRule> detectionRules, int contextBytes) {
        this.detectionRuleUUIDs = detectionRuleUUIDs;
        this.detectionRules = detectionRules;
        this.contextBytes = contextBytes;
    }

    /**
     * Get the detection rule UUIDs.
     *
     * @return the detection rule UUIDs to use to scan the file
     */
    public List<UUID> getDetectionRuleUUIDs() {
        return detectionRuleUUIDs;
    }

    /**
     * Set the detection rule UUIDs.
     *
     * @param detectionRuleUUIDs the detection rule UUIDs to use to scan the file
     */
    public void setDetectionRuleUUIDs(List<UUID> detectionRuleUUIDs) {
        this.detectionRuleUUIDs = detectionRuleUUIDs;
    }

    /**
     * Get the detection rule UUIDs.
     *
     * @return the detection rules to use to scan the file
     */
    public List<DetectionRule> getDetectionRules() {
        return detectionRules;
    }

    /**
     * Set the detection rules.
     *
     * @param detectionRules the detection rules to use to scan the file
     */
    public void setDetectionRules(List<DetectionRule> detectionRules) {
        this.detectionRules = detectionRules;
    }

    /**
     * Get the number of context bytes.
     *
     * @return the number of bytes of context (leading and trailing) to return with any matched findings
     */
    public int getContextBytes() {
        return contextBytes;
    }

    /**
     * Set the number of context bytes.
     *
     * @param contextBytes the number of bytes of context (leading and trailing) to return with any matched findings
     */
    public void setContextBytes(int contextBytes) {
        this.contextBytes = contextBytes;
    }

    @Override
    public String toString() {
        return "ScanTextConfig{"
                + "detectionRuleUUIDs=" + detectionRuleUUIDs
                + ", detectionRules=" + detectionRules
                + ", contextBytes=" + contextBytes
                + '}';
    }
}
