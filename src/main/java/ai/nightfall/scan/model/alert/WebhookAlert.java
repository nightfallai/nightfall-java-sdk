package ai.nightfall.scan.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The WebhookAlert class contains the configuration required to allow clients to send a webhook event to an
 * external URL when findings are detected. The URL provided must (1) use the HTTPS scheme, (2) have a
 * route defined on the HTTP POST method, and (3) return a 200 status code upon receipt of the event.
 *
 * <p>In contrast to other platforms, when using the file scanning APIs, an alert is also sent to this webhook
 * *even when there are no findings*.
 */
public class WebhookAlert {

    @JsonProperty("address")
    private String address;

    /**
     * Creates an instance of the WebhookAlert class with the provided URL.
     *
     * @param address a URL address
     */
    public WebhookAlert(String address) {
        this.address = address;
    }

    /**
     * Returns the URL address to which alerts should be delivered.
     *
     * @return the URL address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the URL address to which alerts should be delivered.
     *
     * @param address the URL address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "WebhookAlert{"
                + "address='" + address + '\''
                + '}';
    }
}
