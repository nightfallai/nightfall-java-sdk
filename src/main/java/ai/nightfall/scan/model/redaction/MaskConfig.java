package ai.nightfall.scan.model.redaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * An object that specifies how findings should be masked when returned by the API.
 */
public class MaskConfig {

    // TODO (emf): need to test if char works for UTF-8 char's: emojis, asian characters, ...

    @JsonProperty("maskingChar")
    private String maskingChar;

    @JsonProperty("charsToIgnore")
    private String[] charsToIgnore;

    @JsonProperty("numCharsToLeaveUnmasked")
    private int numCharsToLeaveUnmasked;

    @JsonProperty("maskLeftToRight")
    private boolean maskLeftToRight;

    /**
     * Default constructor; when no masking char is provided, defaults to '*'.
     */
    public MaskConfig() {
        this.charsToIgnore = new String[0];
        this.numCharsToLeaveUnmasked = 0;
        this.maskLeftToRight = false;
    }

    /**
     * Builds a masking configuration with the provided masking character.
     *
     * @param maskingChar the masking character; this character may be a multi-byte character, but it must be
     *                    exactly one codepoint.
     */
    public MaskConfig(String maskingChar) {
        this();
        this.maskingChar = maskingChar;
        this.charsToIgnore = new String[0];
    }

    /**
     * Builds a masking configuration with the provided masking character and characters to ignore.
     *
     * @param maskingChar the masking character; this character may be a multi-byte character, but it must be
     *                    exactly one codepoint.
     * @param charsToIgnore the set of characters to leave unmasked when the finding is returned. These characters may
     *                      be multi-byte characters, but each entry in the array must be exactly one codepoint.
     */
    public MaskConfig(String maskingChar, String[] charsToIgnore) {
        this.maskingChar = maskingChar;
        this.charsToIgnore = charsToIgnore;
    }

    /**
     * Returns the masking character.
     *
     * @return the masking character
     */
    public String getMaskingChar() {
        return maskingChar;
    }

    /**
     * Sets the masking character.
     *
     * @param maskingChar the masking character
     */
    public void setMaskingChar(String maskingChar) {
        this.maskingChar = maskingChar;
    }

    /**
     * Returns the characters to ignore during masking.
     *
     * @return the characters to ignore during masking
     */
    public String[] getCharsToIgnore() {
        return charsToIgnore;
    }

    /**
     * Sets the characters to ignore during masking.
     *
     * @param charsToIgnore the characters to ignore during masking
     */
    public void setCharsToIgnore(String[] charsToIgnore) {
        this.charsToIgnore = charsToIgnore;
    }

    /**
     * Returns the number of characters to leave unmasked, defaults to 0.
     *
     * @return the number of characters to leave unmasked, defaults to 0
     */
    public int getNumCharsToLeaveUnmasked() {
        return numCharsToLeaveUnmasked;
    }

    /**
     * Sets the number of characters to leave unmasked.
     *
     * @param numCharsToLeaveUnmasked the number of characters to leave unmasked
     */
    public void setNumCharsToLeaveUnmasked(int numCharsToLeaveUnmasked) {
        this.numCharsToLeaveUnmasked = numCharsToLeaveUnmasked;
    }

    /**
     * Returns true if masking should be applied left-to-right, false otherwise. Defaults to false.
     *
     * @return true if masking should be applied left-to-right, false otherwise
     */
    public boolean isMaskLeftToRight() {
        return maskLeftToRight;
    }

    /**
     * Sets whether masking should be applied left-to-right.
     *
     * @param maskLeftToRight true if masking should be applied left-to-right, false otherwise
     */
    public void setMaskLeftToRight(boolean maskLeftToRight) {
        this.maskLeftToRight = maskLeftToRight;
    }

    @Override
    public String toString() {
        return "MaskConfig{"
                + "maskingChar=" + maskingChar
                + ", charsToIgnore=" + Arrays.toString(charsToIgnore)
                + ", numCharsToLeaveUnmasked=" + numCharsToLeaveUnmasked
                + ", maskLeftToRight=" + maskLeftToRight
                + '}';
    }
}
