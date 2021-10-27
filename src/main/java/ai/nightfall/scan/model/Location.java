package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object representing where a finding was discovered in content.
 */
public class Location {

    @JsonProperty("byteRange")
    private Range byteRange;

    @JsonProperty("codepointRange")
    private Range codepointRange;

    /**
     *
     * @return the byte range in which a finding was detected
     */
    public Range getByteRange() {
        return byteRange;
    }

    /**
     *
     * @return the codepoint range in which a finding was detected. This differs from byte range since a codepoint
     * may contain multiple bytes.
     */
    public Range getCodepointRange() {
        return codepointRange;
    }

    @Override
    public String toString() {
        return "Location{" +
                "byteRange=" + byteRange +
                ", codepointRange=" + codepointRange +
                '}';
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
         *
         * @return the beginning of the range
         */
        public long getStart() {
            return start;
        }

        /**
         *
         * @return the end of the range
         */
        public long getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "Range{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
