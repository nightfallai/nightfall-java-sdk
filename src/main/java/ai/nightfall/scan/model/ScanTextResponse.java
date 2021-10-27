package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * The response object returned by a text scan request. Each index <code>i</code> in the field <code>findings</code>
 * corresponds one-to-one with the input request <code>payload</code>, so all findings stored in a given sub-list
 * refer to matches that occurred in the <code>i</code>th index of the request payload.
 */
public class ScanTextResponse {
    @JsonProperty("findings")
    private List<List<Finding>> findings;

    /**
     * Get the findings.
     *
     * @return the findings
     */
    public List<List<Finding>> getFindings() {
        return findings;
    }
}
