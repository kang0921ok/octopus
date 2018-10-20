package osc.gobaby.octopus.service.query.schema.entity;

import java.util.Date;

public class Query {
    private String id;
    private String queryName; //topic & datasource
    private String dimension;
    private String metric;
    private String userId;
    private Date updateDate;
    private boolean startIndexing = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isStartIndexing() {
        return startIndexing;
    }

    public void setStartIndexing(boolean startIndexing) {
        this.startIndexing = startIndexing;
    }
}
