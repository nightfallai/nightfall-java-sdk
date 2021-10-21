package ai.nightfall.scan.model;

/**
 * The super class for all exceptions thrown by Nightfall.
 */
public class BaseNightfallException extends RuntimeException {
    /**
     * Create a new exception.
     * @param message the error message
     */
    public BaseNightfallException(String message) {
        super(message);
    }
}
