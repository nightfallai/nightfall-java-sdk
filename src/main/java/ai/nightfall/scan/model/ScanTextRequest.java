package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

/**
 * An object representing a request to scan inline plaintext with the Nightfall API.
 */
public class ScanTextRequest {
    @JsonProperty("payload")
    private List<String> payload;

    @JsonProperty("policy")
    private ScanTextConfig policy;

    @JsonProperty("policyUUIDs")
    private List<UUID> policyUUIDs;

    /**
     * Create a request to scan the provided <code>payload</code> against the provided scanning
     * <code>policy</code>.
     *
     * @param payload the content to scan
     * @param policy the configuration to use to scan the content
     */
    public ScanTextRequest(List<String> payload, ScanTextConfig policy) {
        this.payload = payload;
        this.policy = policy;
    }

    /**
     * Create a request to scan the provided <code>payload</code> against the provided
     * <code>policyUUIDs</code>.
     *
     * @param payload the content to scan
     * @param policyUUIDs a list of UUIDs referring to pre-created policies to-be-used when scanning. Maximum 1.
     */
    public ScanTextRequest(List<String> payload, List<UUID> policyUUIDs) {
        this.payload = payload;
        this.policyUUIDs = policyUUIDs;
    }

    /**
     * Get the request payload.
     *
     * @return the request data to scan
     */
    public List<String> getPayload() {
        return payload;
    }

    /**
     * Set the request payload.
     *
     * @param payload the request data to scan
     */
    public void setPayload(List<String> payload) {
        this.payload = payload;
    }

    /**
     * Get the request scan policy. Same as <code>getPolicy</code>, just provided for backwards compatibility.
     *
     * @return the configuration to use to scan the <code>payload</code> data
     */
    public ScanTextConfig getConfig() {
        return getPolicy();
    }

    /**
     * Get the request scan policy.
     *
     * @return the configuration to use to scan the <code>payload</code> data
     */
    public ScanTextConfig getPolicy() {
        return policy;
    }

    /**
     * Set the request scan policy. Same as <code>setPolicy</code>, just provided for backwards compatibility.
     *
     * @param config the configuration to use to scan the <code>payload</code> data
     */
    public void setConfig(ScanTextConfig config) {
        setPolicy(config);
    }

    /**
     * Set the request scan policy.
     *
     * @param policy the configuration to use to scan the <code>payload</code> data
     */
    public void setPolicy(ScanTextConfig policy) {
        this.policy = policy;
    }

    /**
     * Get the policy UUIDs to use when performing a scan.
     *
     * @return the policy UUIDs.
     */
    public List<UUID> getPolicyUUIDs() {
        return policyUUIDs;
    }

    /**
     * Set the policy UUIDs to use when performing a scan.
     *
     * @param policyUUIDs the policy UUIDs.
     */
    public void setPolicyUUIDs(List<UUID> policyUUIDs) {
        this.policyUUIDs = policyUUIDs;
    }
}
