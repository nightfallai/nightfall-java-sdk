package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A list of words that can be used to customize the behavior of a detector while Nightfall performs a scan.
 */
public class WordList {
    @JsonProperty("values")
    private List<String> values;

    @JsonProperty("isCaseSensitive")
    private boolean isCaseSensitive;

    /**
     * Creates a new WordList object
     * @param values a list of words
     * @param isCaseSensitive whether Nightfall needs to consider case sensitivity when searching for matches
     */
    public WordList(List<String> values, boolean isCaseSensitive) {
        this.values = values;
        this.isCaseSensitive = isCaseSensitive;
    }

    /**
     *
     * @return a list of words
     */
    public List<String> getValues() {
        return values;
    }

    /**
     *
     * @return true if Nightfall needs to consider case sensitivity when searching for matches
     * in the list, false otherwise
     */
    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }
}
