package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

/**
 * An object that represents a data type or category of information. Detectors are used to scan content
 * for findings.
 */
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

    /**
     * Create an instance of a detector based on a pre-built Nightfall detector
     * @param nightfallDetector the name of a pre-built Nightfall detector
     */
    public Detector(String nightfallDetector) {
        this.detectorType = "NIGHTFALL_DETECTOR";
        this.nightfallDetector = nightfallDetector;
    }

    /**
     * Create an instance of a detector based on a regular expression
     * @param regex the regular expression configuration
     */
    public Detector(Regex regex) {
        this.detectorType = "REGEX";
        this.regex = regex;
    }

    /**
     * Create an instance of a detector based on a word list
     * @param wordList the word list configuration
     */
    public Detector(WordList wordList) {
        this.detectorType = "WORD_LIST";
        this.wordList = wordList;
    }

    /**
     * Create an instance of a detector by using an existing detector's UUID
     * @param detectorUUID an existing detector's UUID
     */
    public Detector(UUID detectorUUID) {
        // no detector type; implicit in UUID definition
        this.detectorUUID = detectorUUID;
    }


    /**
     *
     * @return the minimum confidence threshold required in order for a finding to be triggered
     */
    public String getMinConfidence() {
        return minConfidence;
    }

    /**
     *
     * @param minConfidence the minimum confidence threshold. Valid values: <code>VERY_UNLIKELY</code>, <code>UNLIKELY</code>,
     *                      <code>POSSIBLE</code>, <code>LIKELY</code>, <code>VERY_LIKELY</code>.
     */
    public void setMinConfidence(String minConfidence) {
        this.minConfidence = minConfidence;
    }

    /**
     *
     * @return the minimum number of occurrences of the detector required to trigger a finding match
     */
    public int getMinNumFindings() {
        return minNumFindings;
    }

    /**
     *
     * @param minNumFindings the minimum number of occurrences of the detector required to trigger a finding match
     */
    public void setMinNumFindings(int minNumFindings) {
        this.minNumFindings = minNumFindings;
    }

    /**
     *
     * @return a UUID that represents a pre-built detector
     */
    public UUID getDetectorUUID() {
        return detectorUUID;
    }

    /**
     *
     * @return a display name for this detector
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName a display name for this detector
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return the type of this detector. Valid values are <code>NIGHTFALL_DETECTOR</code>, <code>REGEX</code>, and <code>WORD_LIST</code>.
     */
    public String getDetectorType() {
        return detectorType;
    }

    /**
     *
     * @return the pre-built Nightfall Detector to use. This field is only used if the Detector was constructed
     * with a Nightfall Detector argument.
     */
    public String getNightfallDetector() {
        return nightfallDetector;
    }

    /**
     *
     * @return the regular expression representing this detector. This field is only used if the Detector was
     * constructed with a regex argument.
     */
    public Regex getRegex() {
        return regex;
    }

    /**
     *
     * @return the word list representing this detector. This field is only used if the Detector was constructed
     * with a word list argument.
     */
    public WordList getWordList() {
        return wordList;
    }

    /**
     *
     * @return the context rules that will be used to customize the behavior of this detector
     */
    public List<ContextRule> getContextRules() {
        return contextRules;
    }

    /**
     *
     * @param contextRules the context rules to use to customize the behavior of this detector
     */
    public void setContextRules(List<ContextRule> contextRules) {
        this.contextRules = contextRules;
    }

    /**
     *
     * @return the exclusion rules that will be used to customize the behavior of this detector
     */
    public List<ExclusionRule> getExclusionRules() {
        return exclusionRules;
    }

    /**
     *
     * @param exclusionRules the exclusion rules to use to customize the behavior of this detector
     */
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
