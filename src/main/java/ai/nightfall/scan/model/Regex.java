package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Regex {

    @JsonProperty("pattern")
    private String pattern;

    @JsonProperty("isCaseSensitive")
    private boolean isCaseSensitive;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        isCaseSensitive = caseSensitive;
    }
}
