package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Proximity {

    @JsonProperty("windowBefore")
    private int windowBefore;

    @JsonProperty("windowAfter")
    private int windowAfter;

    public int getWindowBefore() {
        return windowBefore;
    }

    public int getWindowAfter() {
        return windowAfter;
    }
}
