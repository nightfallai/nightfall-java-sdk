package ai.nightfall.scan.model.redaction;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object that specifies how findings should be encrypted when returned by the API. Currently, encryption
 * is only supported for RSA public keys.
 */
public class CryptoConfig {

    @JsonProperty("publicKey")
    private String publicKey;

    /**
     * Builds a configuration object with the provided PEM-formatted RSA public key.
     *
     * @param publicKey a PEM-formatted RSA public key.
     */
    public CryptoConfig(String publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * Get the public key.
     *
     * @return the public key
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * Set the public key.
     *
     * @param publicKey the public key
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public String toString() {
        return "CryptoConfig{"
                + "publicKey='" + publicKey + '\''
                + '}';
    }
}
