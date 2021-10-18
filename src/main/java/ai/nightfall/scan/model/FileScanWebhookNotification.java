package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * The request payload that is sent by Nightfall to a client-configured webhook URL to report the findings from
 * an asynchronous file scan. The findings themselves live in an external location referred to by `findingsURL`,
 * and will remain accessible until the time described by the field `validUntil`.
 *
 * The `findingsURL` must be considered sensitive; although the data stored at the URL is secure, the
 * URL itself grants a temporary lease so that anyone with the link may download the data.
 */
public class FileScanWebhookNotification {

    @JsonProperty("findingsURL")
    private String findingsURL;

    @JsonProperty("validUntil")
    private Date validUntil;

    @JsonProperty("uploadID")
    private UUID uploadID;

    @JsonProperty("findingsPresent")
    private boolean findingsPresent;

    @JsonProperty("requestMetadata")
    private String requestMetadata;

    @JsonProperty("errors")
    private List<NightfallErrorResponse> errors;

    public String getFindingsURL() {
        return findingsURL;
    }

    public void setFindingsURL(String findingsURL) {
        this.findingsURL = findingsURL;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public UUID getUploadID() {
        return uploadID;
    }

    public void setUploadID(UUID uploadID) {
        this.uploadID = uploadID;
    }

    public boolean isFindingsPresent() {
        return findingsPresent;
    }

    public void setFindingsPresent(boolean findingsPresent) {
        this.findingsPresent = findingsPresent;
    }

    public String getRequestMetadata() {
        return requestMetadata;
    }

    public void setRequestMetadata(String requestMetadata) {
        this.requestMetadata = requestMetadata;
    }

    public List<NightfallErrorResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<NightfallErrorResponse> errors) {
        this.errors = errors;
    }
}
