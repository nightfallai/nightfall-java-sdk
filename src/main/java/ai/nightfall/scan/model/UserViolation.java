package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User details associated with a violation
 */
public class UserViolation {
    @JsonProperty("id")
    private String id;

    @JsonProperty("displayName")
    private String displayName;

    public UserViolation(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    /**
     * Get the id.
     *
     * @return the id of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id.
     *
     * @param id the id of the user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the display name.
     *
     * @return the display name of the user
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Set the display name.
     *
     * @param displayName the display name of the user
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
