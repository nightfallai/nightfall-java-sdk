package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Describes how to adjust confidence on a given finding. Valid values for the adjustment are `VERY_UNLIKELY`,
 * `UNLIKELY`, `POSSIBLE`, `LIKELY`, and `VERY_LIKELY`.
 */
public class ConfidenceAdjustment {
    @JsonProperty("fixedConfidence")
    private String fixedConfidence;

    /**
     * Create a ConfidenceAdjustment object
     * @param fixedConfidence the confidence to adjust to if external criteria are met.
     */
    public ConfidenceAdjustment(String fixedConfidence) {
        this.fixedConfidence = fixedConfidence;
    }

    /**
     *
     * @return the confidence to adjust to
     */
    public String getFixedConfidence() {
        return fixedConfidence;
    }
}
