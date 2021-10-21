package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * An object representing a request to scan inline plaintext with the Nightfall API.
 */
public class ScanTextRequest {
    @JsonProperty("payload")
    private List<String> payload;

    @JsonProperty("config")
    private ScanTextConfig config;

    /**
     * Create a request to scan the provided `payload` against the provided scanning configuration `config`
     * @param payload the content to scan
     * @param config the configuration to use to scan the content
     */
    public ScanTextRequest(List<String> payload, ScanTextConfig config) {
        this.payload = payload;
        this.config = config;
    }

    /**
     *
     * @return the request data to scan
     */
    public List<String> getPayload() {
        return payload;
    }

    /**
     *
     * @param payload the request data to scan
     */
    public void setPayload(List<String> payload) {
        this.payload = payload;
    }

    /**
     *
     * @return the configuration to use to scan the `payload` data
     */
    public ScanTextConfig getConfig() {
        return config;
    }

    /**
     *
     * @param config the configuration to use to scan the `payload` data
     */
    public void setConfig(ScanTextConfig config) {
        this.config = config;
    }
}
