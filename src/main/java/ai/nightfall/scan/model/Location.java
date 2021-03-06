package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object representing where a finding was discovered in content.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    @JsonProperty("byteRange")
    private Range byteRange;

    @JsonProperty("codepointRange")
    private Range codepointRange;

    @JsonProperty("commitHash")
    private String commitHash;

    @JsonProperty("commitAuthor")
    private String commitAuthor;

    @JsonProperty("rowRange")
    private Range rowRange;

    @JsonProperty("columnRange")
    private Range columnRange;

    /**
     * Get the finding's byte range.
     *
     * @return the byte range in which a finding was detected
     */
    public Range getByteRange() {
        return byteRange;
    }

    /**
     * Get the finding's codepoint range.
     *
     * @return the codepoint range in which a finding was detected. This differs from byte range since a codepoint
     *      may contain multiple bytes.
     */
    public Range getCodepointRange() {
        return codepointRange;
    }

    /**
     * Get the finding's row range.
     *
     * @return the row range in which a finding was detected, if it is tabular.
     */
    public Range getRowRange() {
        return rowRange;
    }

    /**
     * Get the finding's column range.
     *
     * @return the column range in which a finding was detected, if it is tabular.
     */
    public Range getColumnRange() {
        return columnRange;
    }

    /**
     * Get the finding's commit hash.
     *
     * @return the hash of the commit in which a finding was detected if known.
     */
    public String getCommitHash() {
        return commitHash;
    }

    /**
     * Get the finding's commit author.
     *
     * @return the author of the commit in which a finding was detected if known.
     */
    public String getCommitAuthor() {
        return commitAuthor;
    }
   
    @Override
    public String toString() {
        return "Location{"
                + "byteRange=" + byteRange
                + ", codepointRange=" + codepointRange
                + ", commitHash=" + commitHash
                + ", commitAuthor=" + commitAuthor
                + '}';
    }

    /**
     * An object that contains references to the start and end of the eponymous range.
     */
    public static class Range {
        @JsonProperty("start")
        private long start;

        @JsonProperty("end")
        private long end;

        /**
         * Get the beginning of the range.
         *
         * @return the beginning of the range
         */
        public long getStart() {
            return start;
        }

        /**
         * Get the end of the range.
         *
         * @return the end of the range
         */
        public long getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "Range{"
                    + "start=" + start
                    + ", end=" + end
                    + '}';
        }
    }
}
