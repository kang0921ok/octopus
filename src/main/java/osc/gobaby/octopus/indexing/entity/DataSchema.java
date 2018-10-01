package osc.gobaby.octopus.indexing.entity;

import osc.gobaby.octopus.indexing.entity.metrics.MetricsSpec;

import java.util.List;

public class DataSchema  {
    private String dataSource;
    private Parser parser;
    private List<MetricsSpec> metricsSpec;
    private QranularitySpec granularitySpec;

    public DataSchema(String dataSource, List<String> dimensionList, List<MetricsSpec> metricsSpecList) {
        this.dataSource = dataSource;
        this.parser = new Parser(dimensionList);
        this.metricsSpec = metricsSpecList;
        this.granularitySpec = new QranularitySpec();
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public List<MetricsSpec> getMetricsSpec() {
        return metricsSpec;
    }

    public void setMetricsSpec(List<MetricsSpec> metricsSpec) {
        this.metricsSpec = metricsSpec;
    }

    public QranularitySpec getGranularitySpec() {
        return granularitySpec;
    }

    public void setGranularitySpec(QranularitySpec granularitySpec) {
        this.granularitySpec = granularitySpec;
    }

    static class Parser{
        private String type;
        private ParseSpec parseSpec;

        public Parser(List<String> dimensionList) {
            this.type = "string";
            this.parseSpec = new ParseSpec(dimensionList);
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public ParseSpec getParseSpec() {
            return parseSpec;
        }

        public void setParseSpec(ParseSpec parseSpec) {
            this.parseSpec = parseSpec;
        }

        static class ParseSpec{
            private String format;
            private TimestampSpec timestampSpec;
            private DimensionsSpec dimensionsSpec;

            public ParseSpec(List<String> dimensionList) {
                this.format = "json";
                this.timestampSpec = new TimestampSpec();
                this.dimensionsSpec = new DimensionsSpec(dimensionList);
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }

            public TimestampSpec getTimestampSpec() {
                return timestampSpec;
            }

            public void setTimestampSpec(TimestampSpec timestampSpec) {
                this.timestampSpec = timestampSpec;
            }

            public DimensionsSpec getDimensionsSpec() {
                return dimensionsSpec;
            }

            public void setDimensionsSpec(DimensionsSpec dimensionsSpec) {
                this.dimensionsSpec = dimensionsSpec;
            }

            static class TimestampSpec{
                private String column;
                private String format;

                public TimestampSpec() {
                    this.column = "timestamp";
                    this.format = "auto";
                }

                public String getColumn() {
                    return column;
                }

                public void setColumn(String column) {
                    this.column = column;
                }

                public String getFormat() {
                    return format;
                }

                public void setFormat(String format) {
                    this.format = format;
                }
            }

            static class DimensionsSpec {
                private List<String> dimensions;

                public DimensionsSpec(List<String> dimensions) {
                    this.dimensions = dimensions;
                }

                public List<String> getDimensions() {
                    return dimensions;
                }

                public void setDimensions(List<String> dimensions) {
                    this.dimensions = dimensions;
                }
            }
        }
    }

    static class QranularitySpec{
        private String type;
        private String segmentGranularity;
        private String queryGranularity;

        public QranularitySpec() {
            this.type = "uniform";
            this.segmentGranularity = "DAY";
            this.queryGranularity = "HOUR";
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSegmentGranularity() {
            return segmentGranularity;
        }

        public void setSegmentGranularity(String segmentGranularity) {
            this.segmentGranularity = segmentGranularity;
        }

        public String getQueryGranularity() {
            return queryGranularity;
        }

        public void setQueryGranularity(String queryGranularity) {
            this.queryGranularity = queryGranularity;
        }
    }
}
