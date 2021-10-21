package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object that describes how a regular expression may be used to adjust the confidence of a candidate finding.
 * This context rule will be applied within the provided byte proximity, and if the regular expression matches, then
 * the confidence associated with the finding will be adjusted to the value prescribed.
 */
public class ContextRule {

    @JsonProperty("regex")
    private Regex regex;

    @JsonProperty("proximity")
    private Proximity proximity;

    @JsonProperty("confidenceAdjustment")
    private ConfidenceAdjustment confidenceAdjustment;

    /**
     * Create a new Context Rule
     * @param regex the regular expression configuration
     * @param proximity the proximity in which to evaluate the regular expression
     * @param confidenceAdjustment describes how to adjust the confidence of a finding if the regular expression matches
     */
    public ContextRule(Regex regex, Proximity proximity, ConfidenceAdjustment confidenceAdjustment) {
        this.regex = regex;
        this.proximity = proximity;
        this.confidenceAdjustment = confidenceAdjustment;
    }

    /**
     * @return the regular expression
     */
    public Regex getRegex() {
        return regex;
    }

    /**
     * @return the proximity
     */
    public Proximity getProximity() {
        return proximity;
    }

    /**
     * @return the confidence adjustment
     */
    public ConfidenceAdjustment getConfidenceAdjustment() {
        return confidenceAdjustment;
    }
}
