package osc.gobaby.octopus.indexing.entity;

import osc.gobaby.octopus.indexing.entity.metrics.MetricsSpec;

import java.util.List;

public class IndexingOrderQuery {
    private String type;
    private DataSchema dataSchema;
    private TuningConfig tuningConfig;
    private IoConfig ioConfig;

    public IndexingOrderQuery(String dataSource, List<String> dimensionList, List<MetricsSpec> metricsSpecList, String topic, String kafkaBroker) {
        this.type = "kafka";
        this.dataSchema = new DataSchema(dataSource, dimensionList, metricsSpecList);
        this.tuningConfig = new TuningConfig();
        this.ioConfig = new IoConfig(topic, kafkaBroker);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataSchema getDataSchema() {
        return dataSchema;
    }

    public void setDataSchema(DataSchema dataSchema) {
        this.dataSchema = dataSchema;
    }

    public TuningConfig getTuningConfig() {
        return tuningConfig;
    }

    public void setTuningConfig(TuningConfig tuningConfig) {
        this.tuningConfig = tuningConfig;
    }

    public IoConfig getIoConfig() {
        return ioConfig;
    }

    public void setIoConfig(IoConfig ioConfig) {
        this.ioConfig = ioConfig;
    }
}
