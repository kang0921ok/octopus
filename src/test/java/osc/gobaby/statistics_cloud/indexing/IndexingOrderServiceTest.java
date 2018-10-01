package osc.gobaby.statistics_cloud.indexing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;
import osc.gobaby.statistics_cloud.indexing.entity.IndexingOrderQuery;
import osc.gobaby.statistics_cloud.indexing.entity.metrics.MetricsSpec;
import osc.gobaby.statistics_cloud.indexing.entity.metrics.MetricsSpecCount;
import osc.gobaby.statistics_cloud.indexing.entity.metrics.MetricsSpecField;
import osc.gobaby.statistics_cloud.indexing.entity.metrics.MetricsType;
import osc.gobaby.statistics_cloud.query.schema.entity.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexingOrderServiceTest {

    @Autowired
    private IndexingOrderService indexingOrderService;

    @Test
    public void indexingOrderTest() {
        // given
        AdminServer overlordServer = new AdminServer();
        overlordServer.setIp("dev-nsp-stats02-ncl.nfra.io");
        overlordServer.setPort("8090");

        AdminServer kafkaServer = new AdminServer();
        kafkaServer.setIp("dev-nsp-stats02-ncl.nfra.io");
        kafkaServer.setPort("6667");

        Query query = new Query();
        query.setQueryName("wikipedia3");
        query.setDimension("channel,cityName,comment,countryIsoCode,countryName,isAnonymous,isMinor,isNew,isRobot,isUnpatrolled,metroCode,namespace,page,regionIsoCode,regionName,user");
        query.setMetric("");

        // when
        indexingOrderService.indexingOrder(overlordServer, kafkaServer, query);

        // then
    }

    @Test
    public void indexingOrderQueryTest() throws JsonProcessingException {
        // given
        ObjectMapper objectMapper = new ObjectMapper();

        List<String> dimenstionList = Arrays.asList("g", "r", "b", "o", "y");
        List<MetricsSpec> metricsSpecList = new ArrayList<>();
        metricsSpecList.add(new MetricsSpecCount());
        metricsSpecList.add(new MetricsSpecField("good", MetricsType.MIN));
        metricsSpecList.add(new MetricsSpecField("good", MetricsType.MAX));
        metricsSpecList.add(new MetricsSpecField("good", MetricsType.SUM));

        IndexingOrderQuery indexingOrderQuery = new IndexingOrderQuery("dataSource", dimenstionList, metricsSpecList, "testTopic", "testKafkaBroker");

        // when
        String str = objectMapper.writeValueAsString(indexingOrderQuery);
        str = str.replace("bootstrapServers", "bootstrap.servers");

        System.out.println(str);
        // then
        assertNotNull(str);
    }

}