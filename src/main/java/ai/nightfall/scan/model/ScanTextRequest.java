package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

    public List<String> getPayload() {
        return payload;
    }

    public void setPayload(List<String> payload) {
        this.payload = payload;
    }

    public ScanTextConfig getConfig() {
        return config;
    }

    public void setConfig(ScanTextConfig config) {
        this.config = config;
    }
}
