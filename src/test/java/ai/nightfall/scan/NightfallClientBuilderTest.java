package ai.nightfall.scan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the NightfallClient.Builder.
 */
public class NightfallClientBuilderTest {

    @Test
    public void testHappyPath() {
        NightfallClient client = new NightfallClient.Builder()
                .withAPIKey("foo") // only argument for builder with no default value
                .build();
        assertNotNull(client);
    }

    @Test
    public void testDefault_missingAPIKey() {
        assertThrows(IllegalArgumentException.class, NightfallClient.Builder::defaultClient);
    }

    @ParameterizedTest
    @CsvSource(value = {
        // invalid upload concurrency
        "-1, 60, 50, 40, 30, 20",
        "0, 60, 50, 40, 30, 20",
        "101, 60, 50, 40, 30, 20",
        // invalid connection timeout
        "100, -1, 50, 40, 30, 20",
        "100, 61, 50, 40, 30, 20",
        // invalid read timeout
        "100, 60, -1, 40, 30, 20",
        "100, 60, 121, 40, 30, 20",
        // invalid write timeout
        "100, 60, 50, -1, 30, 20",
        "100, 60, 50, 121, 30, 20",
        // invalid max idle connections
        "100, 60, 50, 40, 0, 20",
        "100, 60, 50, 40, 501, 20",
        // invalid keep alive
        "100, 60, 50, 40, 30, -1",
        "100, 60, 50, 40, 30, 121",
    })
    public void testInvalid(int uploadConcurrency, int connTimeout, int readTimeout,
                            int writeTimeout, int maxIdleConns, int keepAliveSec) {
        assertThrows(IllegalArgumentException.class, () -> {
            new NightfallClient.Builder()
                    .withAPIKey("foo")
                    .withFileUploadConcurrency(uploadConcurrency)
                    .withConnectionTimeout(Duration.ofSeconds(connTimeout))
                    .withReadTimeout(Duration.ofSeconds(readTimeout))
                    .withWriteTimeout(Duration.ofSeconds(writeTimeout))
                    .withMaxIdleConnections(maxIdleConns)
                    .withKeepAliveDuration(Duration.ofSeconds(keepAliveSec))
                    .build();
            fail("build should not have succeeded");
        });
    }
}
