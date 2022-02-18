package ai.nightfall.scan.model.alert;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The AlertConfig class allows clients to specify where alerts should be delivered when findings are discovered as
 * part of a scan. These alerts are delivered asynchronously to all destinations specified in the object instance.
 */
public class AlertConfig {

    @JsonProperty("email")
    private EmailAlert emailAlert;

    @JsonProperty("slack")
    private SlackAlert slackAlert;

    @JsonProperty("url")
    private WebhookAlert webhookAlert;

    /**
     * Build an instance of AlertConfig with all provided alert destinations.
     *
     * @param emailAlert the email alert destination
     * @param slackAlert the Slack alert destination
     * @param webhookAlert the webhook alert destination
     */
    public AlertConfig(EmailAlert emailAlert, SlackAlert slackAlert, WebhookAlert webhookAlert) {
        this.emailAlert = emailAlert;
        this.slackAlert = slackAlert;
        this.webhookAlert = webhookAlert;
    }

    /**
     * Build an instance of AlertConfig that sends findings alerts via webhook.
     *
     * @param webhookAlert the webhook alert destination
     */
    public AlertConfig(WebhookAlert webhookAlert) {
        this.webhookAlert = webhookAlert;
    }

    /**
     * Build an instance of AlertConfig that sends findings alerts via Slack.
     *
     * @param slackAlert the Slack alert destination
     */
    public AlertConfig(SlackAlert slackAlert) {
        this.slackAlert = slackAlert;
    }

    /**
     * Build an instance of AlertConfig that sends findings alerts via email.
     *
     * @param emailAlert the email alert destination
     */
    public AlertConfig(EmailAlert emailAlert) {
        this.emailAlert = emailAlert;
    }

    /**
     * Returns the email alert destination.
     *
     * @return the email alert destination
     */
    public EmailAlert getEmailAlert() {
        return emailAlert;
    }

    /**
     * Sets the email alert destination.
     *
     * @param emailAlert the email alert destination
     */
    public void setEmailAlert(EmailAlert emailAlert) {
        this.emailAlert = emailAlert;
    }

    /**
     * Returns the Slack alert destination.
     *
     * @return the Slack alert destination
     */
    public SlackAlert getSlackAlert() {
        return slackAlert;
    }

    /**
     * Sets the Slack alert destination.
     *
     * @param slackAlert the Slack alert destination
     */
    public void setSlackAlert(SlackAlert slackAlert) {
        this.slackAlert = slackAlert;
    }

    /**
     * Returns the webhook alert destination.
     *
     * @return the webhook alert destination
     */
    public WebhookAlert getWebhookAlert() {
        return webhookAlert;
    }

    /**
     * Sets the webhook alert destination.
     *
     * @param webhookAlert the webhook alert destination
     */
    public void setWebhookAlert(WebhookAlert webhookAlert) {
        this.webhookAlert = webhookAlert;
    }

    @Override
    public String toString() {
        return "AlertConfig{"
                + "emailAlert=" + emailAlert
                + ", slackAlert=" + slackAlert
                + ", webhookAlert=" + webhookAlert
                + '}';
    }
}
