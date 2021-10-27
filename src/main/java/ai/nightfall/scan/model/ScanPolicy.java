package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

/**
 * An object containing configuration that describes how to scan a file. Since the file is scanned asynchronously,
 * the results from the scan are delivered to the provided webhook URL. The scan configuration may contain both
 * inline detection rule definitions and UUID's referring to existing detection rules (up to 10 of each).
 */
public class ScanPolicy {

    @JsonProperty("webhookURL")
    private String webhookURL;

    @JsonProperty("detectionRules")
    private List<DetectionRule> detectionRules;

    @JsonProperty("detectionRuleUUIDs")
    private List<UUID> detectionRuleUUIDs;

    /**
     * Create a scan policy with the provided detection rules.
     * @param detectionRules a list of detection rules to use to scan content. maximum length 10.
     * @param webhookURL the URL that Nightfall will use to deliver a webhook containing scan results
     * @return the scan policy
     */
    public static ScanPolicy fromDetectionRules(List<DetectionRule> detectionRules, String webhookURL) {
        return new ScanPolicy(webhookURL, detectionRules, null);
    }

    /**
     * Create a scan policy with the provided detection rules.
     * @param detectionRuleUUIDs a list of detection rule UUIDs to use to scan content. maximum length 10.
     * @param webhookURL the URL that Nightfall will use to deliver a webhook containing scan results
     * @return the scan policy
     */
    public static ScanPolicy fromDetectionRuleUUIDs(List<UUID> detectionRuleUUIDs, String webhookURL) {
        return new ScanPolicy(webhookURL, null, detectionRuleUUIDs);
    }

    /**
     * Create a scan policy with the provided detection rules and detection rule UUIDs.
     * @param webhookURL the URL that Nightfall will use to deliver a webhook containing scan results
     * @param detectionRules a list of detection rules to use to scan content. maximum length 10.
     * @param detectionRuleUUIDs a list of detection rule UUIDs to use to scan content. maximum length 10.
     */
    public ScanPolicy(String webhookURL, List<DetectionRule> detectionRules, List<UUID> detectionRuleUUIDs) {
        this.webhookURL = webhookURL;
        this.detectionRules = detectionRules;
        this.detectionRuleUUIDs = detectionRuleUUIDs;
    }

    /**
     *
     * @return the webhook URL that Nightfall should deliver results to
     */
    public String getWebhookURL() {
        return webhookURL;
    }

    /**
     *
     * @param webhookURL the webhook URL that Nightfall should deliver results to
     */
    public void setWebhookURL(String webhookURL) {
        this.webhookURL = webhookURL;
    }

    /**
     *
     * @return the list of detection rules to use to scan the files
     */
    public List<DetectionRule> getDetectionRules() {
        return detectionRules;
    }

    /**
     *
     * @param detectionRules a list of detection rules to scan against
     */
    public void setDetectionRules(List<DetectionRule> detectionRules) {
        this.detectionRules = detectionRules;
    }

    /**
     *
     * @return the list of detection rule UUIDs to scan against
     */
    public List<UUID> getDetectionRuleUUIDs() {
        return detectionRuleUUIDs;
    }

    /**
     *
     * @param detectionRuleUUIDs the list of detection rule UUIDs to scan against
     */
    public void setDetectionRuleUUIDs(List<UUID> detectionRuleUUIDs) {
        this.detectionRuleUUIDs = detectionRuleUUIDs;
    }
}
