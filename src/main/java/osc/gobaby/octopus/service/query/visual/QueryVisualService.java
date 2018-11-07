package osc.gobaby.octopus.service.query.visual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.octopus.service.admin.server.AdminServerService;
import osc.gobaby.octopus.service.admin.server.entity.AdminServer;

@Service
public class QueryVisualService {

    @Autowired
    private QueryVisualConnector queryVisualConnector;

    @Autowired
    private AdminServerService adminServerService;

    public String reqeustNativeQuery(String userId, String nativeQuery){

        AdminServer druidBrokerServer = adminServerService.findDruidBrokerServer();

        return reqeustNativeQuery(druidBrokerServer, nativeQuery);
    }

    public String reqeustNativeQuery(AdminServer druidBrokerServer, String nativeQuery){

        return queryVisualConnector.requestNativeQuery(druidBrokerServer, nativeQuery);
    }

    public String reqeustSqlQuery(String userId, String sqlQuery){

        AdminServer druidBrokerServer = adminServerService.findDruidBrokerServer();

        return reqeustSqlQuery(druidBrokerServer, sqlQuery);
    }

    public String reqeustSqlQuery(AdminServer druidBrokerServer, String sqlQuery){

        return queryVisualConnector.requestSqlQuery(druidBrokerServer, sqlQuery);
    }
}
