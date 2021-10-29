package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * An object that contains a set of detectors to be used when scanning content. Findings matches are
 * triggered according to the provided <code>logicalOp</code>logicalOp; valid values are <code>ANY</code> (logical
 * <code>OR</code>, i.e. a finding is emitted only if any of the provided detectors match), or <code>ALL</code>
 * (logical <code>AND</code>, i.e. a finding is emitted only if all provided detectors match).
 */
public class DetectionRule {

    @JsonProperty("detectors")
    private List<Detector> detectors;

    @JsonProperty("logicalOp")
    private String logicalOp;

    @JsonProperty("name")
    private String name;

    /**
     * Create a detection rule with the provided detectors and logicalOp.
     *
     * @param detectors a list of detectors
     * @param logicalOp a logical op: ANY or ALL
     */
    public DetectionRule(List<Detector> detectors, String logicalOp) {
        this.detectors = detectors;
        this.logicalOp = logicalOp;
    }

    /**
     * Create a detection rule with the provided detectors and logicalOp.
     *
     * @param detectors a list of detectors
     * @param logicalOp a logical op: ANY or ALL
     * @param name a name for the detection rule
     */
    public DetectionRule(List<Detector> detectors, String logicalOp, String name) {
        this.detectors = detectors;
        this.logicalOp = logicalOp;
        this.name = name;
    }

    /**
     * Get the list of detectors.
     *
     * @return the set of detectors
     */
    public List<Detector> getDetectors() {
        return detectors;
    }

    /**
     * Set the detectors.
     *
     * @param detectors a set of detectors
     */
    public void setDetectors(List<Detector> detectors) {
        this.detectors = detectors;
    }

    /**
     * Get the logical op.
     *
     * @return the logical op
     */
    public String getLogicalOp() {
        return logicalOp;
    }

    /**
     * Set the logical op.
     *
     * @param logicalOp a logical op; valid values <code>ANY</code> or <code>ALL</code>
     */
    public void setLogicalOp(String logicalOp) {
        this.logicalOp = logicalOp;
    }

    /**
     * Get the name of the detection rule.
     *
     * @return the name of the detection rule
     */
    public String getName() {
        return name;
    }

    /**
     * Set the detection rule name.
     *
     * @param name a name for the detection rule
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DetectionRule{"
                + "detectors=" + detectors
                + ", logicalOp='" + logicalOp + '\''
                + ", name='" + name + '\''
                + '}';
    }
}
