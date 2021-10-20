package ai.nightfall.scan.model;

public class NightfallAPIException extends BaseNightfallException {

    private NightfallErrorResponse error;
    private int httpStatusCode;

    public NightfallAPIException(String message) {
        super(message);
    }

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

    public NightfallErrorResponse getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return this.error.getMessage();
    }
}
