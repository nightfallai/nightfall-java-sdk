package ai.nightfall.scan.model;

/**
 * The exception thrown when the Nightfall API returns an HTTP status code in the range [400, 599]. Embeds the
 * standard Nightfall error object for debugging.
 */
public class NightfallAPIException extends BaseNightfallException {

    /**
     * the error returned by the Nightfall API
     */
    private NightfallErrorResponse error;

    /**
     * the HTTP status code returned by the Nightfall API
     */
    private int httpStatusCode;

    /**
     * Create a new instance of the exception.
     * @param message an error message
     */
    public NightfallAPIException(String message) {
        super(message);
    }

    /**
     * Create a new instance of the exception.
     * @param message an error message
     * @param error the standardized error object returned by the Nightfall API
     * @param httpStatusCode the HTTP status code
     */
    public NightfallAPIException(String message, NightfallErrorResponse error, int httpStatusCode) {
        super(message);
        this.error = error;
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public String toString() {
        return "NightfallAPIException{" +
                "error=" + error +
                ", httpStatusCode=" + httpStatusCode +
                '}';
    }

    /**
     *
     * @return the standard error object that was returned by the Nightfall API
     */
    public NightfallErrorResponse getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return this.error.getMessage();
    }
}
