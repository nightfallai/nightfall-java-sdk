package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Describes how to adjust confidence on a given finding. Valid values for the adjustment are
 * <code>VERY_UNLIKELY</code>, <code>UNLIKELY</code>, <code>POSSIBLE</code>, <code>LIKELY</code>,
 * and <code>VERY_LIKELY</code>.
 */
public class ConfidenceAdjustment {
    @JsonProperty("fixedConfidence")
    private String fixedConfidence;

    /**
     * Create a ConfidenceAdjustment object.
     *
     * @param fixedConfidence the confidence to adjust to if external criteria are met.
     */
    public ConfidenceAdjustment(String fixedConfidence) {
        this.fixedConfidence = fixedConfidence;
    }

    /**
     * Return the confidence to adjust to.
     *
     * @return the confidence to adjust to
     */
    public String getFixedConfidence() {
        return fixedConfidence;
    }
}
