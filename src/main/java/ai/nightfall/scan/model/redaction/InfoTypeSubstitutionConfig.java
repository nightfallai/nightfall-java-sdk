package ai.nightfall.scan.model.redaction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * An object that specifies that findings should be substituted with the name of the info type that matched
 * the request data.
 */
@JsonSerialize
public class InfoTypeSubstitutionConfig {

    @Override
    public String toString() {
        return "InfoTypeSubstitutionConfig{}";
    }
}
