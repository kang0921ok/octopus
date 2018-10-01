package osc.gobaby.octopus.indexing.entity;

public class TuningConfig {
    private String type;
    private long maxRowsPerSegment;

    public TuningConfig() {
        this.type = "kafka";
        this.maxRowsPerSegment = 5000000;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getMaxRowsPerSegment() {
        return maxRowsPerSegment;
    }

    public void setMaxRowsPerSegment(long maxRowsPerSegment) {
        this.maxRowsPerSegment = maxRowsPerSegment;
    }
}
