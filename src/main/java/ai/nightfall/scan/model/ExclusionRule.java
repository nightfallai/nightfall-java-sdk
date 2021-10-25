package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object that describes a regular expression or list of keywords that may be used to disqualify a
 * candidate finding from triggering a detector match.
 */
public class ExclusionRule {

    @JsonProperty("matchType")
    private String matchType;

    @JsonProperty("exclusionType")
    private String exclusionType;

    @JsonProperty("regex")
    private Regex regex;

    @JsonProperty("wordList")
    private WordList wordList;

    /**
     * Create an exclusion rule that uses a regular expression to make a disqualification decision.
     *
     * @param regex the regular expression
     * @param matchType the match type; valid values are FULL or PARTIAL
     */
    public ExclusionRule(Regex regex, String matchType) {
        this.regex = regex;
        this.matchType = matchType;
        this.exclusionType = "REGEX";
    }

    /**
     * Create an exclusion rule that uses a word list to make a disqualification decision.
     *
     * @param wordList the word list
     * @param matchType the match type; valid values are FULL or PARTIAL
     */
    public ExclusionRule(WordList wordList, String matchType) {
        this.wordList = wordList;
        this.matchType = matchType;
        this.exclusionType = "WORD_LIST";
    }

    /**
     * Get the match type.
     *
     * @return the match type represented by this exclusion rule. Valid values are <code>PARTIAL</code> or
     *      <code>FULL</code>.
     */
    public String getMatchType() {
        return matchType;
    }

    /**
     * Get the exclusion type.
     *
     * @return the type of this exclusion rule. Valid values are <code>WORD_LIST</code> or <code>REGEX</code>.
     *      The corresponding field in this object must be set according to the value specified here.
     */
    public String getExclusionType() {
        return exclusionType;
    }

    /**
     * Get the regex.
     *
     * @return the regular expression to use to evaluate the exclusion rule
     */
    public Regex getRegex() {
        return regex;
    }

    /**
     * Get the word list.
     *
     * @return the word list to use to evaluate the exclusion rule
     */
    public WordList getWordList() {
        return wordList;
    }
}
