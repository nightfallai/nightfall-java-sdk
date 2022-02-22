package ai.nightfall.scan.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The EmailAlert class contains the configuration required to allow clients to send an asynchronous email message
 * when findings are detected. The findings themselves will be delivered as a file attachment on the email.
 * Alerts are only sent if findings are detected.
 */
public class EmailAlert {

    @JsonProperty("address")
    private String address;

    /**
     * Creates an instance of the EmailAlert class with the provided email address.
     *
     * @param address an email address
     */
    public EmailAlert(String address) {
        this.address = address;
    }

    /**
     * Returns the email address to which alerts should be delivered.
     *
     * @return the email address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the email address to which alerts should be delivered.
     *
     * @param address the email address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EmailAlert{"
                + "address='" + address + '\''
                + '}';
    }
}
