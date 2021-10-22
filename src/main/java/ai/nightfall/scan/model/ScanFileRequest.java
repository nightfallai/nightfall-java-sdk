package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * A container for a request to scan a file that was uploaded via the Nightfall API. Exactly one of <code>policyUUID</code>
 * or <code>policy</code> should be provided.
 */
public class ScanFileRequest {

    @JsonProperty("policyUUID")
    private UUID policyUUID;

    @JsonProperty("policy")
    private ScanPolicy policy;

    @JsonProperty("requestMetadata")
    private String requestMetadata;

    /**
     * Create a new request to scan a file.
     * @param policy the policy to use to scan the file.
     * @param requestMetadata arbitrary metadata to pass along with the request; maximum length 10 KB.
     */
    public ScanFileRequest(ScanPolicy policy, String requestMetadata) {
        this.policy = policy;
        this.requestMetadata = requestMetadata;
    }

    /**
     * Create a new request to scan a file.
     * @param policyUUID the UUID of an existing policy to use to scan the file.
     * @param requestMetadata arbitrary metadata to pass along with the request; maximum length 10 KB.
     */
    public ScanFileRequest(UUID policyUUID, String requestMetadata) {
        this.policyUUID = policyUUID;
        this.requestMetadata = requestMetadata;
    }

    /**
     *
     * @return the UUID of an existing policy to use to scan a file
     */
    public UUID getPolicyUUID() {
        return policyUUID;
    }

    /**
     *
     * @return the policy to use to scan the file
     */
    public ScanPolicy getPolicy() {
        return policy;
    }

    /**
     *
     * @return the request metadata.
     */
    public String getRequestMetadata() {
        return requestMetadata;
    }

    /**
     *
     * @param requestMetadata arbitrary data to be passed along with the request, maximum length 10 KB.
     */
    public void setRequestMetadata(String requestMetadata) {
        this.requestMetadata = requestMetadata;
    }
}
