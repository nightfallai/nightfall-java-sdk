package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * The response object returned by a text scan request. Each index `i` in the field `findings` corresponds one-to-one
 * with the input request `payload`, so all findings stored in a given sub-list refer to matches that occurred
 * in the `i`th index of the request payload.
 */
public class ScanTextResponse {
    @JsonProperty("findings")
    private List<List<Finding>> findings;

    /**
     *
     * @return the findings
     */
    public List<List<Finding>> getFindings() {
        return findings;
    }
}
