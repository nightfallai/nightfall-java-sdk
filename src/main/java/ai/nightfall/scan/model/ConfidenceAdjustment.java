package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfidenceAdjustment {
    @JsonProperty("fixedConfidence")
    private String fixedConfidence;

    public String getFixedConfidence() {
        return fixedConfidence;
    }

    public void setFixedConfidence(String fixedConfidence) {
        this.fixedConfidence = fixedConfidence;
    }
}
