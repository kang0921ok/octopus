package osc.gobaby.statistics_cloud.query.schema;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import osc.gobaby.statistics_cloud.query.schema.QuerySchemaService;
import osc.gobaby.statistics_cloud.query.schema.entity.Query;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class QuerySchemaServiceTest {

    @Autowired
    private QuerySchemaService querySchemaService;

    @Test
    public void createQueryTest(){
        // given
        Query query = new Query();
        query.setQueryId("testId");
        query.setDimension("g,f,g");
        query.setMetric("g,d,f");

        // when
        boolean actual = querySchemaService.createQuery(query);

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

        querySchemaService.createQuery(query);
        querySchemaService.createQuery(query);
        querySchemaService.createQuery(query);

        // when
        List<Query> actual = querySchemaService.findQueryList();

        // then
        assertNotNull(actual);
    }

}