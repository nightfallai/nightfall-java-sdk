package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * The request payload that is sent by Nightfall to a client-configured webhook URL to report the findings from
 * an asynchronous file scan. The findings themselves live in an external location referred to by
 * <code>findingsURL</code>, and will remain accessible until the time described by the field <code>validUntil</code>.
 *
 * <p>The <code>findingsURL</code> must be considered sensitive; although the data stored at the URL is secure, the
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

    /**
     * Get the findings URL.
     *
     * @return a URL referring to the location where findings may be downloaded from
     */
    public String getFindingsURL() {
        return findingsURL;
    }

    /**
     * Get the findings URL expiry time.
     *
     * @return the point in time when the provided findings URL expires. After this date elapses, the findings
     *      will no longer be accessible via this URL.
     */
    public Date getValidUntil() {
        return validUntil;
    }

    /**
     * Get the file ID.
     *
     * @return the file ID associated with these scan results
     */
    public UUID getUploadID() {
        return uploadID;
    }

    /**
     * Get whether findings are present.
     *
     * @return true if and only if any findings were detected in the file represented by <code>uploadID</code>,
     *      otherwise false.
     */
    public boolean isFindingsPresent() {
        return findingsPresent;
    }

    /**
     * Get the request metadata.
     *
     * @return the metadata that the client provided with the initial request, often used for correlating the file
     *      that this scan result represents.
     */
    public String getRequestMetadata() {
        return requestMetadata;
    }

    /**
     * Get the list of errors that occurred while processing the request.
     *
     * @return a list of errors that was encountered by the API while scanning the file.
     */
    public List<NightfallErrorResponse> getErrors() {
        return errors;
    }
}
