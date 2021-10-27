package ai.nightfall.scan;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;

/**
 * A class that implements Nightfall webhook signature validation. This class can be used in a request middleware
 * to validate the authenticity of a request before processing it. Validation is implemented with a SHA-256 HMAC signature.
 */
public class WebhookSignatureValidator {

    // This constant is documented in https://docs.oracle.com/javase/8/docs/api/javax/crypto/Mac.html
    private static final String SHA256 = "HmacSHA256";
    private static final TemporalAmount DEFAULT_THRESHOLD = Duration.ofMinutes(5);

    private final TemporalAmount threshold;

    /**
     * Instantiates the validator with the default threshold.
     */
    public WebhookSignatureValidator() {
        this.threshold = DEFAULT_THRESHOLD;
    }

    /**
     * Instantiates the validator with the provided threshold.
     * @param threshold the time threshold within which webhook requests should be considered valid.
     */
    public WebhookSignatureValidator(TemporalAmount threshold) {
        this.threshold = threshold;
    }

    /**
     * Validates that the provided request payload is an authentic request that originated from Nightfall. If this
     * method returns false, request handlers shall not process the provided body any further.
     * @param requestBody the entire, raw request payload, encoded in UTF-8.
     * @param signingSecret the signing secret used as the key for HMAC.
     * @param requestSignature the signature provided by Nightfall to compare against the locally-computed value.
     * @param requestTimestamp the Unix timestamp of when this request was sent, i.e. the number of seconds since the Unix epoch.
     * @return true if the signature is valid and the request occurred within the allowed time threshold, otherwise false.
     */
    public boolean validate(String requestBody, byte[] signingSecret, String requestSignature, String requestTimestamp) {
        if (requestBody == null || signingSecret == null || requestSignature == null || requestTimestamp == null) {
            return false;
        }

        Instant now = Instant.now();
        Instant reqTime = Instant.parse(requestTimestamp);
        if (now.minus(this.threshold).isAfter(reqTime)) {
            return false;
        }

        Mac hmac;
        try {
            hmac = Mac.getInstance(SHA256);
            Key key = new SecretKeySpec(signingSecret, SHA256);
            hmac.init(key);
        } catch (NoSuchAlgorithmException e) {
            // should not happen, all JREs are required to implement HmacSHA256
            return false;
        } catch (InvalidKeyException e) {
            // e.g. invalid signing secret
            return false;
        }

        String hashPayload = requestTimestamp + ":" + requestBody;
        byte[] hashed = hmac.doFinal(hashPayload.getBytes(StandardCharsets.UTF_8));
        String hexHash = String.format("%032x", new BigInteger(hashed));
        return hexHash.equals(requestSignature);
    }
}
