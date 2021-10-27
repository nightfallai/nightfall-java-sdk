package ai.nightfall.scan.model;

/**
 * The exception thrown when the Nightfall client is unable to process a request due to an unexpected error.
 */
public class NightfallClientException extends BaseNightfallException {
    /**
     * Create a new instance of this exception.
     * @param message an error message
     */
    public NightfallClientException(String message) {
        super(message);
    }
}
