package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ScanFileRequest {

    @JsonProperty("policyUUID")
    private UUID policyUUID;

    @JsonProperty("policy")
    private ScanPolicy policy;

    @JsonProperty("requestMetadata")
    private String requestMetadata;

    public ScanFileRequest(ScanPolicy policy, String requestMetadata) {
        this.policy = policy;
        this.requestMetadata = requestMetadata;
    }

    public ScanFileRequest(UUID policyUUID, String requestMetadata) {
        this.policyUUID = policyUUID;
        this.requestMetadata = requestMetadata;
    }

    public UUID getPolicyUUID() {
        return policyUUID;
    }

    public void setPolicyUUID(UUID policyUUID) {
        this.policyUUID = policyUUID;
    }

    public ScanPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(ScanPolicy policy) {
        this.policy = policy;
    }

    public String getRequestMetadata() {
        return requestMetadata;
    }

    public void setRequestMetadata(String requestMetadata) {
        this.requestMetadata = requestMetadata;
    }
}
