package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContextRule {

    @JsonProperty("regex")
    private Regex regex;

    @JsonProperty("proximity")
    private Proximity proximity;

    @JsonProperty("confidenceAdjustment")
    private ConfidenceAdjustment confidenceAdjustment;

    public Regex getRegex() {
        return regex;
    }

    public void setRegex(Regex regex) {
        this.regex = regex;
    }

    public Proximity getProximity() {
        return proximity;
    }

    public void setProximity(Proximity proximity) {
        this.proximity = proximity;
    }

    public ConfidenceAdjustment getConfidenceAdjustment() {
        return confidenceAdjustment;
    }

    public void setConfidenceAdjustment(ConfidenceAdjustment confidenceAdjustment) {
        this.confidenceAdjustment = confidenceAdjustment;
    }
}
