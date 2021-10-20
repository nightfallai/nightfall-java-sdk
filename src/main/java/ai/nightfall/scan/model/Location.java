package ai.nightfall.scan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    @JsonProperty("byteRange")
    private Range byteRange;

    @JsonProperty("codepointRange")
    private Range codepointRange;

    public Range getByteRange() {
        return byteRange;
    }

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

    public static class Range {
        @JsonProperty("start")
        private long start;

        @JsonProperty("end")
        private long end;

        public long getStart() {
            return start;
        }

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
