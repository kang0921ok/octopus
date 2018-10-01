package osc.gobaby.octopus.indexing.entity.metrics;

public class MetricsSpecCount implements MetricsSpec{
    private String name;
    private String type;

    public MetricsSpecCount() {
        this.name = "count";
        this.type = "count";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
