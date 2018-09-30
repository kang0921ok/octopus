package osc.gobaby.statistics_cloud.indexing.entity.metrics;

public class MetricsSpecField implements MetricsSpec{
    private String name;
    private String fieldName;
    private String type;

    public MetricsSpecField(String fieldName, MetricsType metricsType) {
        this.name = fieldName + "_" + metricsType.getNamePostfix();
        this.fieldName = fieldName;
        this.type = "double"+metricsType.getTypePostfix();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
