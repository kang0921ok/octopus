package osc.gobaby.octopus.service.indexing.entity.metrics;

public enum MetricsType {
    MIN("min","Min"),
    MAX("max","Max"),
    SUM("sum","Sum");

    private String namePostfix;
    private String typePostfix;

    MetricsType(String namePostfix, String typePostfix) {
        this.namePostfix = namePostfix;
        this.typePostfix = typePostfix;
    }

    public String getNamePostfix() {
        return namePostfix;
    }

    public void setNamePostfix(String namePostfix) {
        this.namePostfix = namePostfix;
    }

    public String getTypePostfix() {
        return typePostfix;
    }

    public void setTypePostfix(String typePostfix) {
        this.typePostfix = typePostfix;
    }
}
