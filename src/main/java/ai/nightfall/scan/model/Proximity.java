package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object representing a range of bytes to consider around a candidate finding.
 */
public class Proximity {

    @JsonProperty("windowBefore")
    private int windowBefore;

    @JsonProperty("windowAfter")
    private int windowAfter;

    /**
     * Construct a new Proximity.
     *
     * @param windowBefore The number of leading characters to consider as context
     * @param windowAfter The number of trailing characters to consider as context
     */
    public Proximity(int windowBefore, int windowAfter) {
        this.windowBefore = windowBefore;
        this.windowAfter = windowAfter;
    }

    /**
     * Get the number of leading bytes.
     *
     * @return the number of bytes to consider leading up to a candidate finding
     */
    public int getWindowBefore() {
        return windowBefore;
    }

    /**
     * Get the number of trailing bytes.
     *
     * @return the number of bytes to consider trailing a candidate finding
     */
    public int getWindowAfter() {
        return windowAfter;
    }
}
