package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViolationAction {
    enum Action {
        UNDEFINED
    }
    
    @JsonProperty("action")
    private Action action;

    public ViolationAction(Action action) {
        this.action = action;
    }

    /**
     * Get the action.
     *
     * @return the action
     */
    public Action get() {
        return action;
    }

    /**
     * Set the action.
     *
     * @param action the action
     */
    public void set(Action action) {
        this.action = action;
    }

}
