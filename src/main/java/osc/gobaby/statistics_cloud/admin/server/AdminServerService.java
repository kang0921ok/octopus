package osc.gobaby.statistics_cloud.admin.server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;
import osc.gobaby.statistics_cloud.admin.server.repository.AdminServerMapper;
import osc.gobaby.statistics_cloud.controller.exception.NoMandatoryKeyException;

import java.util.List;

@Service
public class AdminServerService {

    @Autowired
    private AdminServerMapper adminServerMapper;

    public boolean createAdminServer(AdminServer adminServer) {
        int insertNum = adminServerMapper.insert(adminServer);

        return insertNum == 1 ? true : false;
    }

    public boolean modifyAdminServer(AdminServer adminServer) throws NoMandatoryKeyException {
        if(StringUtils.isBlank(adminServer.getId())){
            throw new NoMandatoryKeyException("id");
        }

        int modifyNum = adminServerMapper.update(adminServer);

        return modifyNum == 1 ? true : false;
    }

    public List<AdminServer> findAdminServerList() {
        return adminServerMapper.selectList();
    }

    public AdminServer findKafkaServer() {
        return adminServerMapper.selectKafkaType();
    }

    public AdminServer findDruidOverlordServer() {
        return adminServerMapper.selectDruidOverlordType();
    }

    public AdminServer findDruidBrokerServer() {
        return adminServerMapper.selectDruidBrokerType();
    }
}
