package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class ScanTextConfig {

    @JsonProperty("detectionRuleUUIDs")
    private List<UUID> detectionRuleUUIDs;

    @JsonProperty("detectionRules")
    private List<DetectionRule> detectionRules;

    @JsonProperty("contextBytes")
    private int contextBytes;

    /**
     * Create a scan configuration with the provided detection rules.
     * @param detectionRuleUUIDs a list of detection rules to use to scan content
     * @param contextBytes the number of bytes of context (leading and trailing) to return with any matched findings
     * @return the scan configuration
     */
    public static ScanTextConfig fromDetectionRuleUUIDs(List<UUID> detectionRuleUUIDs, int contextBytes) {
        return new ScanTextConfig(detectionRuleUUIDs, null, contextBytes);
    }

    /**
     * Create a scan configuration with the provided detection rules.
     * @param detectionRules a list of detection rules to use to scan content
     * @param contextBytes the number of bytes of context (leading and trailing) to return with any matched findings
     * @return the scan configuration
     */
    public static ScanTextConfig fromDetectionRules(List<DetectionRule> detectionRules, int contextBytes) {
        return new ScanTextConfig(null, detectionRules, contextBytes);
    }

    public ScanTextConfig(List<UUID> detectionRuleUUIDs, List<DetectionRule> detectionRules, int contextBytes) {
        this.detectionRuleUUIDs = detectionRuleUUIDs;
        this.detectionRules = detectionRules;
        this.contextBytes = contextBytes;
    }

    public List<UUID> getDetectionRuleUUIDs() {
        return detectionRuleUUIDs;
    }

    public void setDetectionRuleUUIDs(List<UUID> detectionRuleUUIDs) {
        this.detectionRuleUUIDs = detectionRuleUUIDs;
    }

    public List<DetectionRule> getDetectionRules() {
        return detectionRules;
    }

    public void setDetectionRules(List<DetectionRule> detectionRules) {
        this.detectionRules = detectionRules;
    }

    public int getContextBytes() {
        return contextBytes;
    }

    public void setContextBytes(int contextBytes) {
        this.contextBytes = contextBytes;
    }

    @Override
    public String toString() {
        return "ScanTextConfig{" +
                "detectionRuleUUIDs=" + detectionRuleUUIDs +
                ", detectionRules=" + detectionRules +
                ", contextBytes=" + contextBytes +
                '}';
    }
}
