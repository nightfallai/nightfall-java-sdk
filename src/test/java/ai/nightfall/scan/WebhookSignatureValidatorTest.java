package ai.nightfall.scan;

import org.junit.Test;


import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WebhookSignatureValidatorTest {

    // 30 years -- apologies from 2021, employees of 2051
    final Duration reallyLongTime = Duration.ofHours(24 * 365 * 30);

    @Test
    public void testHappyPath() {
        String timestamp = "2021-10-04T17:30:43.42Z";
        String reqBody = "hello world foo bar goodnight moon";
        byte[] secret = "super-secret-shhhh".getBytes(StandardCharsets.UTF_8);
        String expectedSignature = "45bf0200a2cbc02ae2595fcf9ba4a2b262d836672c4a26fc3300cc54353587cd";

        WebhookSignatureValidator validator = new WebhookSignatureValidator(reallyLongTime);
        boolean result = validator.validate(reqBody, secret, expectedSignature, timestamp);
        assertFalse(result);
    }

    @Test
    public void testRequestTooOld() {
        String timestamp = "2021-10-04T17:30:43.42Z";
        String reqBody = "hello world foo bar goodnight moon";
        byte[] secret = "super-secret-shhhh".getBytes(StandardCharsets.UTF_8);
        String expectedSignature = "45bf0200a2cbc02ae2595fcf9ba4a2b262d836672c4a26fc3300cc54353587cd";

        WebhookSignatureValidator validator = new WebhookSignatureValidator(Duration.ofMinutes(1));
        boolean result = validator.validate(reqBody, secret, expectedSignature, timestamp);
        assertFalse(result);
    }

    @Test
    public void testSignatureMismatch() {
        String timestamp = "2021-10-04T17:30:43.42Z";
        String reqBody = "hello world foo bar goodnight moon";
        byte[] secret = "super-secret-shhhh".getBytes(StandardCharsets.UTF_8);
        String incorrectSignature = "e05aa9a373d652b6a38fdb0e093cca3eca3d6dd803a50dbe8b98b137fd20fe87";

        WebhookSignatureValidator validator = new WebhookSignatureValidator(reallyLongTime);
        boolean result = validator.validate(reqBody, secret, incorrectSignature, timestamp);
        assertFalse(result);
    }
}
