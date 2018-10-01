package osc.gobaby.octopus.admin.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import osc.gobaby.octopus.admin.server.entity.AdminServer;
import osc.gobaby.octopus.admin.server.entity.AdminServerType;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminServerServiceTest {

    @Autowired
    private AdminServerService adminServerService;

    @Test
    public void createAdminServerTest(){
        // given
        AdminServer adminServer = new AdminServer();
        adminServer.setAdminServerType(AdminServerType.KAFKA);
        adminServer.setIp("211.123.45.2");
        adminServer.setPort("8080");
        adminServer.setUpdateUserId("kang");

        // when
        boolean actual = adminServerService.createAdminServer(adminServer);

        // then
        assertTrue(actual);
    }

    @Test
    public void findAdminServerListTest(){
        // given
        AdminServer adminServer = new AdminServer();
        adminServer.setAdminServerType(AdminServerType.KAFKA);
        adminServer.setIp("211.123.45.2");
        adminServer.setPort("8080");
        adminServer.setUpdateUserId("kang");

        adminServerService.createAdminServer(adminServer);
        adminServerService.createAdminServer(adminServer);
        adminServerService.createAdminServer(adminServer);

        // when
        List<AdminServer> actual = adminServerService.findAdminServerList();

        // then
        assertNotNull(actual);
    }
}