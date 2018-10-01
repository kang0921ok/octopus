package osc.gobaby.statistics_cloud.admin.server.entity;

import java.util.Date;

public class AdminServer {
    private String id;
    private AdminServerType adminServerType;
    private String ip;
    private String port;
    private Date updateDate;
    private String updateUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AdminServerType getAdminServerType() {
        return adminServerType;
    }

    public void setAdminServerType(AdminServerType adminServerType) {
        this.adminServerType = adminServerType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
}
