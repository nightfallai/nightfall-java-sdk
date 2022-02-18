package ai.nightfall.scan.model;

import ai.nightfall.scan.model.alert.AlertConfig;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

/**
 * An object containing configuration that describes how to scan a file. Since the file is scanned asynchronously,
 * the results from the scan are delivered to the provided webhook URL. The scan configuration may contain both
 * inline detection rule definitions and UUID's referring to existing detection rules (up to 10 of each).
 */
public class ScanPolicy {

    @Deprecated
    @JsonProperty("webhookURL")
    private String webhookURL;

    @JsonProperty("detectionRules")
    private List<DetectionRule> detectionRules;

    @JsonProperty("detectionRuleUUIDs")
    private List<UUID> detectionRuleUUIDs;

    @JsonProperty("alertConfig")
    private AlertConfig alertConfig;

    /**
     * Create a scan policy with the provided detection rules.
     *
     * @param detectionRules a list of detection rules to use to scan content. maximum length 10.
     * @param webhookURL the URL that Nightfall will use to deliver a webhook containing scan results
     * @return the scan policy
     */
    public static ScanPolicy fromDetectionRules(List<DetectionRule> detectionRules, String webhookURL) {
        return new ScanPolicy(webhookURL, detectionRules, null);
    }

    /**
     * Create a scan policy with the provided detection rules.
     *
     * @param detectionRuleUUIDs a list of detection rule UUIDs to use to scan content. maximum length 10.
     * @param webhookURL the URL that Nightfall will use to deliver a webhook containing scan results
     * @return the scan policy
     */
    public static ScanPolicy fromDetectionRuleUUIDs(List<UUID> detectionRuleUUIDs, String webhookURL) {
        return new ScanPolicy(webhookURL, null, detectionRuleUUIDs);
    }

    /**
     * Create a scan policy with the provided detection rules and detection rule UUIDs.
     *
     * @param webhookURL the URL that Nightfall will use to deliver a webhook containing scan results
     * @param detectionRules a list of detection rules to use to scan content. maximum length 10.
     * @param detectionRuleUUIDs a list of detection rule UUIDs to use to scan content. maximum length 10.
     */
    @Deprecated
    public ScanPolicy(String webhookURL, List<DetectionRule> detectionRules, List<UUID> detectionRuleUUIDs) {
        this.webhookURL = webhookURL;
        this.detectionRules = detectionRules;
        this.detectionRuleUUIDs = detectionRuleUUIDs;
    }

    /**
     * Create a scan policy with the provided detection rules and detection rule UUIDs.
     *
     * @param alertConfig the alert configuration that Nightfall will use to deliver findings to external sources.
     * @param detectionRules a list of detection rules to use to scan content. maximum length 10.
     * @param detectionRuleUUIDs a list of detection rule UUIDs to use to scan content. maximum length 10.
     */
    @Deprecated
    public ScanPolicy(AlertConfig alertConfig, List<DetectionRule> detectionRules, List<UUID> detectionRuleUUIDs) {
        this.alertConfig = alertConfig;
        this.detectionRules = detectionRules;
        this.detectionRuleUUIDs = detectionRuleUUIDs;
    }

    /**
     * Get the webhook URL. Deprecated: use the <code>alertConfig</code> object instead.
     *
     * @return the webhook URL that Nightfall should deliver results to
     */
    @Deprecated
    public String getWebhookURL() {
        return webhookURL;
    }

    /**
     * Set the webhook URL.
     *
     * @param webhookURL the webhook URL that Nightfall should deliver results to
     */
    @Deprecated
    public void setWebhookURL(String webhookURL) {
        this.webhookURL = webhookURL;
    }

    /**
     * Get the list of detection rules.
     *
     * @return the list of detection rules to use to scan the files
     */
    public List<DetectionRule> getDetectionRules() {
        return detectionRules;
    }

    /**
     * Set the detection rules.
     *
     * @param detectionRules a list of detection rules to scan against
     */
    public void setDetectionRules(List<DetectionRule> detectionRules) {
        this.detectionRules = detectionRules;
    }

    /**
     * Get the detection rule UUIDs.
     *
     * @return the list of detection rule UUIDs to scan against
     */
    public List<UUID> getDetectionRuleUUIDs() {
        return detectionRuleUUIDs;
    }

    /**
     * Set the detection rule UUIDs.
     *
     * @param detectionRuleUUIDs the list of detection rule UUIDs to scan against
     */
    public void setDetectionRuleUUIDs(List<UUID> detectionRuleUUIDs) {
        this.detectionRuleUUIDs = detectionRuleUUIDs;
    }

    /**
     * Get the alert configuration that specifies where alerts should be delivered when findings are detected.
     *
     * @return the alert configuration
     */
    public AlertConfig getAlertConfig() {
        return alertConfig;
    }

    /**
     * Sets the alert configuration that specifies where alerts should be delivered when findings are detected.
     *
     * @param alertConfig the alert configuration
     */
    public void setAlertConfig(AlertConfig alertConfig) {
        this.alertConfig = alertConfig;
    }

}
