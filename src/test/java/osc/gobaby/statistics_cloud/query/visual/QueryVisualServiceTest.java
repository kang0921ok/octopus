package osc.gobaby.statistics_cloud.query.visual;

import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryVisualServiceTest {

    @Autowired
    private QueryVisualService queryVisualService;

    @Test
    public void reqeustNativeQueryTest() {
        // given
        AdminServer druidBrokerServer = new AdminServer();
        druidBrokerServer.setIp("dev-nsp-stats01-ncl.nfra.io");
        druidBrokerServer.setPort("8082");

        String nativeQuery = "{\n" +
                "  \"queryType\" : \"topN\",\n" +
                "  \"dataSource\" : \"wikipedia3\",\n" +
                "  \"intervals\" : [\"2015-09-12/2015-09-13\"],\n" +
                "  \"granularity\" : \"all\",\n" +
                "  \"dimension\" : \"page\",\n" +
                "  \"metric\" : \"count\",\n" +
                "  \"threshold\" : 10,\n" +
                "  \"aggregations\" : [\n" +
                "    {\n" +
                "      \"type\" : \"count\",\n" +
                "      \"name\" : \"count\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        // when
        String actual = queryVisualService.reqeustNativeQuery(druidBrokerServer, nativeQuery);

        // then
        assertNotNull(actual);
    }

}