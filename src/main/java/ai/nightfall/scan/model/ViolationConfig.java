package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Configuration that can be added to a scan request which is used to generate a Nightfall specific violation.
 * This class can currently only be used by Nightfall partners.
 */
public class ViolationConfig {
    @JsonProperty("location")
    private String location;

    @JsonProperty("sublocation")
    private String sublocation;

    @JsonProperty("user")
    private UserViolation user;

    @JsonProperty("action")
    private ViolationAction action;

    @JsonProperty("actionPhrase")
    private String actionPhrase;

    @JsonProperty("properties")
    private Map<String, String> properties;

    public ViolationConfig(String location, String sublocation, UserViolation user, ViolationAction action, String actionPhrase, Map<String, String> properties) {
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
    public ViolationAction getAction() {
        return action;
    }

    /**
     * Set the action.
     *
     * @param action the action associated with the created violation
     */
    public void setAction(ViolationAction action) {
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
