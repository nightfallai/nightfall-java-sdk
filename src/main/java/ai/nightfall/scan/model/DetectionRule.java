package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * An object that contains a set of detectors to be used when scanning content. Findings matches are
 * triggered according to the provided `logicalOp`; valid values are `ANY` (logical `OR`, i.e. a finding
 * is emitted only if any of the provided detectors match), or `ALL` (logical `AND`, i.e. a finding is
 * emitted only if all provided detectors match).
 */
public class DetectionRule {

    @JsonProperty("detectors")
    private List<Detector> detectors;

    @JsonProperty("logicalOp")
    private String logicalOp;

    @JsonProperty("name")
    private String name;

    /**
     *
     * @return the set of detectors
     */
    public List<Detector> getDetectors() {
        return detectors;
    }

    /**
     *
     * @param detectors a set of detectors
     */
    public void setDetectors(List<Detector> detectors) {
        this.detectors = detectors;
    }

    /**
     *
     * @return the logical op
     */
    public String getLogicalOp() {
        return logicalOp;
    }

    /**
     *
     * @param logicalOp a logical op; valid values `ANY` or `ALL`
     */
    public void setLogicalOp(String logicalOp) {
        this.logicalOp = logicalOp;
    }

    /**
     *
     * @return the name of the detection rule
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name a name for the detection rule
     */
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
