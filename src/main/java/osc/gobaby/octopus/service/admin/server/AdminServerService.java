package osc.gobaby.octopus.service.admin.server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.octopus.service.admin.server.entity.AdminServer;
import osc.gobaby.octopus.service.admin.server.repository.AdminServerMapper;
import osc.gobaby.octopus.exception.NoMandatoryKeyException;

import java.util.List;

@Service
public class AdminServerService {

    @Autowired
    private AdminServerMapper adminServerMapper;

    public boolean upsertAdminServer(AdminServer adminServer) throws NoMandatoryKeyException {
        List<AdminServer> adminServerList = findAdminServerList();
        boolean isExist = false;
        for (AdminServer as : adminServerList) {
            if (as.getAdminServerType() == adminServer.getAdminServerType()) {
                isExist = true;
                adminServer.setId(as.getId());
                break;
            }
        }

        if (isExist) {
            modifyAdminServer(adminServer);
        } else {
            createAdminServer(adminServer);
        }

        return true;
    }

    public List<AdminServer> findAdminServerList() {
        return adminServerMapper.selectList();
    }

    protected boolean createAdminServer(AdminServer adminServer) {
        int insertNum = adminServerMapper.insert(adminServer);

        return insertNum == 1 ? true : false;
    }

    protected boolean modifyAdminServer(AdminServer adminServer) throws NoMandatoryKeyException {
        if (StringUtils.isBlank(adminServer.getId())) {
            throw new NoMandatoryKeyException("id");
        }

        int modifyNum = adminServerMapper.update(adminServer);

        return modifyNum == 1 ? true : false;
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
