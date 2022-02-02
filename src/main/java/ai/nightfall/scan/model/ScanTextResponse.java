package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * The response object returned by a text scan request. Each index <code>i</code> in the field <code>findings</code>
 * corresponds one-to-one with the input request <code>payload</code>, so all findings stored in a given sub-list
 * refer to matches that occurred in the <code>i</code>th index of the request payload.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScanTextResponse {
    @JsonProperty("findings")
    private List<List<Finding>> findings;

    @JsonProperty("redactedPayload")
    private List<String> redactedPayload;

    /**
     * Get the findings.
     *
     * @return the findings
     */
    public List<List<Finding>> getFindings() {
        return findings;
    }

    /**
     * Return the original request content with the configured redactions applied. If redaction was not
     * applied for a given input string, the string at a given index in the array will be empty.
     *
     * @return the original content with the configured redactions applied
     */
    public List<String> getRedactedPayload() {
        return redactedPayload;
    }

    @Override
    public String toString() {
        return "ScanTextResponse{"
                + "findings=" + findings
                + ", redactedPayload=" + redactedPayload
                + '}';
    }
}
