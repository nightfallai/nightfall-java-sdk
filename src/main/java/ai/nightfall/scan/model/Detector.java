package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class Detector {

    @JsonProperty("minConfidence")
    private String minConfidence;

    @JsonProperty("minNumFindings")
    private int minNumFindings;

    @JsonProperty("detectorUUID")
    private UUID detectorUUID;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("detectorType")
    private String detectorType;

    @JsonProperty("nightfallDetector")
    private String nightfallDetector;

    @JsonProperty("regex")
    private Regex regex;

    @JsonProperty("wordList")
    private WordList wordList;

    @JsonProperty("contextRules")
    private List<ContextRule> contextRules;

    @JsonProperty("exclusionRules")
    private List<ExclusionRule> exclusionRules;

    public String getMinConfidence() {
        return minConfidence;
    }

    public void setMinConfidence(String minConfidence) {
        this.minConfidence = minConfidence;
    }

    public int getMinNumFindings() {
        return minNumFindings;
    }

    public void setMinNumFindings(int minNumFindings) {
        this.minNumFindings = minNumFindings;
    }

    public UUID getDetectorUUID() {
        return detectorUUID;
    }

    public void setDetectorUUID(UUID detectorUUID) {
        this.detectorUUID = detectorUUID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDetectorType() {
        return detectorType;
    }

    public void setDetectorType(String detectorType) {
        this.detectorType = detectorType;
    }

    public String getNightfallDetector() {
        return nightfallDetector;
    }

    public void setNightfallDetector(String nightfallDetector) {
        this.nightfallDetector = nightfallDetector;
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

    public List<ContextRule> getContextRules() {
        return contextRules;
    }

    public void setContextRules(List<ContextRule> contextRules) {
        this.contextRules = contextRules;
    }

    public List<ExclusionRule> getExclusionRules() {
        return exclusionRules;
    }

    public void setExclusionRules(List<ExclusionRule> exclusionRules) {
        this.exclusionRules = exclusionRules;
    }

    @Override
    public String toString() {
        return "Detector{" +
                "minConfidence='" + minConfidence + '\'' +
                ", minNumFindings=" + minNumFindings +
                ", detectorUUID=" + detectorUUID +
                ", displayName='" + displayName + '\'' +
                ", detectorType='" + detectorType + '\'' +
                ", nightfallDetector='" + nightfallDetector + '\'' +
                ", regex=" + regex +
                ", wordList=" + wordList +
                ", contextRules=" + contextRules +
                ", exclusionRules=" + exclusionRules +
                '}';
    }
}
