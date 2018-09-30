package osc.gobaby.statistics_cloud.admin.server;

import org.springframework.stereotype.Service;
import osc.gobaby.statistics_cloud.admin.server.entity.ConnectEntity;

@Service
public class StatisticsConnectTestService {

    public boolean connectTestKafka(ConnectEntity connectEntity){

        return true;
    }

    public boolean connectTestDruidOverlord(ConnectEntity connectEntity){

        return true;
    }

    public boolean connectTestDruidBroker(ConnectEntity connectEntity){

        return true;
    }
}
