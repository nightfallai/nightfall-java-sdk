package ai.nightfall.scan.model;

/**
 * An exception that indicates that a timeout was exceeded while processing a request
 */
public class NightfallRequestTimeoutException extends BaseNightfallException {
    /**
     * Create a new instance of this exception.
     * @param message an error message
     */
    public NightfallRequestTimeoutException(String message) {
        super(message);
    }
}
