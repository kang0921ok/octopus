package osc.gobaby.octopus.admin.server;

import org.springframework.stereotype.Service;
import osc.gobaby.octopus.admin.server.entity.AdminServer;
import osc.gobaby.octopus.admin.server.entity.AdminServerType;

@Service
public class StatisticsConnectTestService {

    public boolean connectTest(AdminServer adminServer){
        if(adminServer.getAdminServerType() == AdminServerType.DRUID_OVERLOAD){
            return connectTestDruidOverlord(adminServer);
        } else if(adminServer.getAdminServerType() == AdminServerType.DRUID_BROKER){
            return connectTestDruidBroker(adminServer);
        } else {
            return connectTestKafka(adminServer);
        }
    }

    private boolean connectTestKafka(AdminServer adminServer){

        return true;
    }

    private boolean connectTestDruidOverlord(AdminServer adminServer){

        return true;
    }

    private boolean connectTestDruidBroker(AdminServer adminServer){

        return true;
    }
}
