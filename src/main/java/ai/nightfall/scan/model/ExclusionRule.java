package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExclusionRule {

    @JsonProperty("matchType")
    private String matchType;

    @JsonProperty("exclusionType")
    private String exclusionType;

    @JsonProperty("regex")
    private Regex regex;

    @JsonProperty("wordList")
    private WordList wordList;

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getExclusionType() {
        return exclusionType;
    }

    public void setExclusionType(String exclusionType) {
        this.exclusionType = exclusionType;
    }

    public Regex getRegex() {
        return regex;
    }

    public void setRegex(Regex regex) {
        this.regex = regex;
    }

    public WordList getWordList() {
        return wordList;
    }

    public void setWordList(WordList wordList) {
        this.wordList = wordList;
    }
}
