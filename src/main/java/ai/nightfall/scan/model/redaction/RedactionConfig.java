package ai.nightfall.scan.model.redaction;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object that configures how any detected findings should be redacted when returned to the client. When this
 * configuration is provided as part of a request, exactly one of the four types of redaction should be set.
 *
 * <p>Four types of redaction are supported:
 * <ol>
 *     <li>Masking: replacing the characters of a finding with another character, such as '*' or '👀'</li>
 *     <li>Info Type Substitution: replacing the finding with the name of the detector it matched, such
 *     as CREDIT_CARD_NUMBER</li>
 *     <li>Substitution: replacing the finding with a custom string, such as "oh no!"</li>
 *     <li>Encryption: encrypting the finding with an RSA public key</li>
 * </ol>
 */
public class RedactionConfig {

    @JsonProperty("maskConfig")
    private MaskConfig maskConfig;

    @JsonProperty("infoTypeSubstitutionConfig")
    private InfoTypeSubstitutionConfig infoTypeSubstitutionConfig;

    @JsonProperty("substitutionConfig")
    private SubstitutionConfig substitutionConfig;

    @JsonProperty("cryptoConfig")
    private CryptoConfig cryptoConfig;

    @JsonProperty("removeFinding")
    private boolean removeFinding;

    /**
     * Build a redaction config with masking.
     *
     * @param maskConfig the masking configuration
     */
    public RedactionConfig(MaskConfig maskConfig) {
        this.maskConfig = maskConfig;
    }

    /**
     * Build a redaction config with info type substitution.
     *
     * @param infoTypeSubstitutionConfig the info type substitution configuration
     */
    public RedactionConfig(InfoTypeSubstitutionConfig infoTypeSubstitutionConfig) {
        this.infoTypeSubstitutionConfig = infoTypeSubstitutionConfig;
    }

    /**
     * Build a redaction config with substitution.
     *
     * @param substitutionConfig the substitution configuration
     */
    public RedactionConfig(SubstitutionConfig substitutionConfig) {
        this.substitutionConfig = substitutionConfig;
    }

    /**
     * Build a redaction config with RSA encryption.
     *
     * @param cryptoConfig the crypto configuration
     */
    public RedactionConfig(CryptoConfig cryptoConfig) {
        this.cryptoConfig = cryptoConfig;
    }

    /**
     * Get the masking configuration.
     *
     * @return the masking configuration
     */
    public MaskConfig getMaskConfig() {
        return maskConfig;
    }

    /**
     * Get the info type substitution configuration.
     *
     * @return the info type substitution configuration
     */
    public InfoTypeSubstitutionConfig getInfoTypeSubstitutionConfig() {
        return infoTypeSubstitutionConfig;
    }

    /**
     * Get the substitution configuration.
     *
     * @return the substitution configuration
     */
    public SubstitutionConfig getSubstitutionConfig() {
        return substitutionConfig;
    }

    /**
     * Get the encryption configuration.
     *
     * @return the encryption configuration
     */
    public CryptoConfig getCryptoConfig() {
        return cryptoConfig;
    }

    /**
     * Returns whether the original finding should be omitted in responses from the API.
     *
     * @return true if the original finding should be omitted in responses from the API, false otherwise
     */
    public boolean isRemoveFinding() {
        return removeFinding;
    }

    /**
     * Sets whether the original finding should be omitted in responses from the API.
     *
     * @param removeFinding true if the original finding should be omitted in responses from the API, false otherwise
     */
    public void setRemoveFinding(boolean removeFinding) {
        this.removeFinding = removeFinding;
    }

    @Override
    public String toString() {
        return "RedactionConfig{"
                + "maskConfig=" + maskConfig
                + ", infoTypeSubstitutionConfig=" + infoTypeSubstitutionConfig
                + ", substitutionConfig=" + substitutionConfig
                + ", cryptoConfig=" + cryptoConfig
                + ", removeFinding=" + removeFinding
                + '}';
    }
}
