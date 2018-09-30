package osc.gobaby.statistics_cloud.query.visual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;

@Service
public class QueryVisualService {

    @Autowired
    private QueryVisualConnector queryVisualConnector;

    public String reqeustNativeQuery(AdminServer druidBrokerServer, String nativeQuery){

        return queryVisualConnector.requestNativeQuery(druidBrokerServer, nativeQuery);
    }
}
