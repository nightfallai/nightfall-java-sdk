package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class ScanPolicy {

    @JsonProperty("webhookURL")
    private String webhookURL;

    @JsonProperty("detectionRules")
    private List<DetectionRule> detectionRules;

    @JsonProperty("detectionRuleUUIDs")
    private List<UUID> detectionRuleUUIDs;

    /**
     * Create a scan policy with the provided detection rules.
     * @param detectionRules a list of detection rules to use to scan content
     * @param webhookURL the URL that Nightfall will use to deliver a webhook containing scan results
     * @return the scan policy
     */
    public static ScanPolicy fromDetectionRules(List<DetectionRule> detectionRules, String webhookURL) {
        return new ScanPolicy(webhookURL, detectionRules, null);
    }

    /**
     * Create a scan policy with the provided detection rules.
     * @param detectionRuleUUIDs a list of detection rule UUIDs to use to scan content
     * @param webhookURL the URL that Nightfall will use to deliver a webhook containing scan results
     * @return the scan policy
     */
    public static ScanPolicy fromDetectionRuleUUIDs(List<UUID> detectionRuleUUIDs, String webhookURL) {
        return new ScanPolicy(webhookURL, null, detectionRuleUUIDs);
    }

    public ScanPolicy(String webhookURL, List<DetectionRule> detectionRules, List<UUID> detectionRuleUUIDs) {
        this.webhookURL = webhookURL;
        this.detectionRules = detectionRules;
        this.detectionRuleUUIDs = detectionRuleUUIDs;
    }

    public String getWebhookURL() {
        return webhookURL;
    }

    public void setWebhookURL(String webhookURL) {
        this.webhookURL = webhookURL;
    }

    public List<DetectionRule> getDetectionRules() {
        return detectionRules;
    }

    public void setDetectionRules(List<DetectionRule> detectionRules) {
        this.detectionRules = detectionRules;
    }

    public List<UUID> getDetectionRuleUUIDs() {
        return detectionRuleUUIDs;
    }

    public void setDetectionRuleUUIDs(List<UUID> detectionRuleUUIDs) {
        this.detectionRuleUUIDs = detectionRuleUUIDs;
    }
}
