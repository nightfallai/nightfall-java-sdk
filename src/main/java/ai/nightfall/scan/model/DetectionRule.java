package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DetectionRule {

    @JsonProperty("detectors")
    private List<Detector> detectors;

    @JsonProperty("logicalOp")
    private String logicalOp;

    @JsonProperty("name")
    private String name;

    public List<Detector> getDetectors() {
        return detectors;
    }

    public void setDetectors(List<Detector> detectors) {
        this.detectors = detectors;
    }

    public String getLogicalOp() {
        return logicalOp;
    }

    public void setLogicalOp(String logicalOp) {
        this.logicalOp = logicalOp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DetectionRule{" +
                "detectors=" + detectors +
                ", logicalOp='" + logicalOp + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
