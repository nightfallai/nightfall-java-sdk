package ai.nightfall.scan.model.redaction;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object that specifies how findings should be substituted when returned by the API. This is similar
 * to masking, but allows for a custom phrase to be used rather than simply redacting each codepoint.
 */
public class SubstitutionConfig {

    @JsonProperty("substitutionPhrase")
    private String substitutionPhrase;

    /**
     * Builds a substitution configuration with the provided phrase.
     *
     * @param substitutionPhrase a phrase to substitute for a finding.
     */
    public SubstitutionConfig(String substitutionPhrase) {
        this.substitutionPhrase = substitutionPhrase;
    }

    /**
     * Returns the substitution phrase.
     *
     * @return the substitution phrase.
     */
    public String getSubstitutionPhrase() {
        return substitutionPhrase;
    }

    /**
     * Sets the substitution phrase.
     *
     * @param substitutionPhrase the substitution phrase
     */
    public void setSubstitutionPhrase(String substitutionPhrase) {
        this.substitutionPhrase = substitutionPhrase;
    }

    @Override
    public String toString() {
        return "SubstitutionConfig{"
                + "substitutionPhrase='" + substitutionPhrase + '\''
                + '}';
    }
}
