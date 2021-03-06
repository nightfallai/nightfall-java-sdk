package ai.nightfall.scan;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the webhook signature validator module.
 */
public class WebhookSignatureValidatorTest {

    final Duration reallyLongTime = Duration.ofHours(24 * 365 * 1000);

    @Test
    public void testHappyPath() {
        String timestamp = "1633368643";
        String reqBody = "hello world foo bar goodnight moon";
        byte[] secret = "super-secret-shhhh".getBytes(StandardCharsets.UTF_8);
        String expectedSignature = "641ada412da02d7df7ca59e94a556b7f5683374db614565ad3d99da1a9a779fb";

        WebhookSignatureValidator validator = new WebhookSignatureValidator(reallyLongTime);
        boolean result = validator.validate(reqBody, secret, expectedSignature, timestamp);
        assertTrue(result);
    }

    @Test
    public void testRequestTooOld() {
        String timestamp = "1633368643";
        String reqBody = "hello world foo bar goodnight moon";
        byte[] secret = "super-secret-shhhh".getBytes(StandardCharsets.UTF_8);
        String expectedSignature = "641ada412da02d7df7ca59e94a556b7f5683374db614565ad3d99da1a9a779fb";

        WebhookSignatureValidator validator = new WebhookSignatureValidator(Duration.ofMinutes(1));
        boolean result = validator.validate(reqBody, secret, expectedSignature, timestamp);
        assertFalse(result);
    }

    @Test
    public void testRequestTooFarInTheFuture() {
        String timestamp = "4789042243";
        String reqBody = "hello world foo bar goodnight moon";
        byte[] secret = "super-secret-shhhh".getBytes(StandardCharsets.UTF_8);
        String expectedSignature = "641ada412da02d7df7ca59e94a556b7f5683374db614565ad3d99da1a9a779fb";

        WebhookSignatureValidator validator = new WebhookSignatureValidator(Duration.ofMinutes(1));
        boolean result = validator.validate(reqBody, secret, expectedSignature, timestamp);
        assertFalse(result);
    }

    @Test
    public void testSignatureMismatch() {
        String timestamp = "1633368643";
        String reqBody = "hello world foo bar goodnight moon";
        byte[] secret = "super-secret-shhhh".getBytes(StandardCharsets.UTF_8);
        String incorrectSignature = "e05aa9a373d652b6a38fdb0e093cca3eca3d6dd803a50dbe8b98b137fd20fe87";

        WebhookSignatureValidator validator = new WebhookSignatureValidator(reallyLongTime);
        boolean result = validator.validate(reqBody, secret, incorrectSignature, timestamp);
        assertFalse(result);
    }

    @Test
    public void testBytesToHex() {
        byte[][] tests = new byte[][]{
            new byte[]{-1, 1},
            new byte[]{6, 12, 12, 24},
            new byte[]{-14, -63, -34},
            new byte[]{0, 1, 126, 127, -128, -127, -126, -3, -2, -1},
        };
        String[] expectedResults = new String[]{
            "ff01",
            "060c0c18",
            "f2c1de",
            "00017e7f808182fdfeff"
        };

        WebhookSignatureValidator validator = new WebhookSignatureValidator(reallyLongTime);
        for (int i = 0; i < tests.length; i++) {
            String actual = validator.bytesToHex(tests[i]);
            assertEquals(expectedResults[i], actual);
        }
    }
}
