package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Configuration that can be added to a scan request which is used to generate a Nightfall specific violation.
 * This class can currently only be used by Nightfall partners.
 */
public class ViolationConfig {
    public enum Action {
        UNSPECIFIED,
        ENCRYPT,
        BLOCK;
    }

    @JsonProperty("location")
    private String location;

    @JsonProperty("sublocation")
    private String sublocation;

    @JsonProperty("user")
    private UserViolation user;

    @JsonProperty("action")
    private Action action;

    @JsonProperty("actionPhrase")
    private String actionPhrase;

    @JsonProperty("properties")
    private Map<String, String> properties;

    /**
     * Creates a new ViolationConfig object.
     *
     * @param location the location of the content to be scanned
     * @param sublocation the sublocation of the content to be scanned
     * @param user the user associated with the content to be scanned
     * @param action the action to be taken on any findings from the scan request
     * @param actionPhrase the phrase to describe the action to be taken on any findings
     * @param properties a map of key value pairs containing metadata about the content to be scanned
     */
    public ViolationConfig(
        String location, String sublocation, UserViolation user, 
        Action action, String actionPhrase, Map<String, String> properties) {
        this.location = location;
        this.sublocation = sublocation;
        this.user = user;
        this.action = action;
        this.actionPhrase = actionPhrase;
        this.properties = properties;
    }

    /**
     * Get the location.
     *
     * @return the location associated with the created violation
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the location.
     *
     * @param location the location associated with the created violation
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the sublocation.
     *
     * @return the sublocation associated with the created violation
     */
    public String getSublocation() {
        return sublocation;
    }

    /**
     * Set the sublocation.
     *
     * @param sublocation the sublocation associated with the created violation
     */
    public void setSublocation(String sublocation) {
        this.sublocation = sublocation;
    }

    /**
     * Get the user.
     *
     * @return the user associated with the created violation
     */
    public UserViolation getUser() {
        return user;
    }

    /**
     * Set the user.
     *
     * @param user the user associated with the created violation
     */
    public void setUser(UserViolation user) {
        this.user = user;
    }

    /**
     * Get the action.
     *
     * @return the action associated with the created violation
     */
    public Action getAction() {
        return action;
    }

    /**
     * Set the action.
     *
     * @param action the action associated with the created violation
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * Get the action phrase.
     *
     * @return the action phrase associated with the created violation
     */
    public String getActionPhrase() {
        return actionPhrase;
    }

    /**
     * Set the action phrase.
     *
     * @param actionPhrase the action phrase associated with the created violation
     */
    public void setActionPhrase(String actionPhrase) {
        this.actionPhrase = actionPhrase;
    }

    /**
     * Get the properties.
     *
     * @return the properties associated with the created violation
     */
    public Map<String, String> getProperties() {
        return properties;
    }

    /**
     * Set the properties.
     *
     * @param properties the properties associated with the created violation
     */
    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
