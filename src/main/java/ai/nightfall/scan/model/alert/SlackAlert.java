package ai.nightfall.scan.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The SlackAlert class contains the configuration required to allow clients to send asynchronous alerts to a
 * Slack workspace when findings are detected. Note that in order for Slack alerts to be delivered to your workspace,
 * you must use authenticate Nightfall to your Slack workspace under the Settings menu on the
 * <a href="https://app.nightfall.ai/">Nightfall Dashboard</a>. Alerts are only sent if findings are detected.
 *
 * <p>Currently, Nightfall supports delivering alerts to public channels, formatted like "#general".
 */
public class SlackAlert {

    @JsonProperty("target")
    private String target;

    /**
     * Creates an instance of the SlackAlert class with the provided conversation name.
     *
     * @param target a Slack conversation name.
     */
    public SlackAlert(String target) {
        this.target = target;
    }

    /**
     * Returns the target conversation to which alerts should be delivered.
     *
     * @return the name of the target conversation
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the target conversation to which alerts should be delivered.
     *
     * @param target the name of the target conversation
     */
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "SlackAlert{"
                + "target='" + target + '\''
                + '}';
    }
}
