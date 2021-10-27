package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object representing a regular expression to customize the behavior of a detector while Nightfall performs a scan.
 */
public class Regex {

    @JsonProperty("pattern")
    private String pattern;

    @JsonProperty("isCaseSensitive")
    private boolean isCaseSensitive;

    /**
     * Creates a new Regex object.
     *
     * @param pattern the regular expression to use as part of a detector
     * @param isCaseSensitive whether to consider case sensitivity when evaluating matches
     */
    public Regex(String pattern, boolean isCaseSensitive) {
        this.pattern = pattern;
        this.isCaseSensitive = isCaseSensitive;
    }

    /**
     * Get the regex pattern.
     *
     * @return the regular expression to use as part of a detector
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Get whether the regex is case-sensitive.
     *
     * @return true if the regular expression needs to consider case sensitivity when searching for
     *     matches, false otherwise
     */
    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }
}
