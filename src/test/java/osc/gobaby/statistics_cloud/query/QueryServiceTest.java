package osc.gobaby.statistics_cloud.query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import osc.gobaby.statistics_cloud.admin.server.AdminServerService;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServerType;
import osc.gobaby.statistics_cloud.query.entity.Query;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class QueryServiceTest {

    @Autowired
    private QueryService queryService;

    @Test
    public void createQueryTest(){
        // given
        Query query = new Query();
        query.setQueryId("testId");
        query.setDimension("g,f,g");
        query.setMetric("g,d,f");

        // when
        boolean actual = queryService.createQuery(query);

        // then
        assertTrue(actual);
    }

    @Test
    public void findAdminServerListTest(){
        // given
        Query query = new Query();
        query.setQueryId("testId");
        query.setDimension("g,f,g");
        query.setMetric("g,d,f");

        queryService.createQuery(query);
        queryService.createQuery(query);
        queryService.createQuery(query);

        // when
        List<Query> actual = queryService.findQueryList();

        // then
        assertNotNull(actual);
    }

}