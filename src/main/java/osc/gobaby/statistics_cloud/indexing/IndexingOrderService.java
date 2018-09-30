package osc.gobaby.statistics_cloud.indexing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
public class IndexingOrderService {

    @Autowired
    private IndexingOrderConnector indexingOrderConnector;

    public boolean indexingOrder(AdminServer overlordServer, AdminServer kafkaServer, Query query) {
        // indexing service 요청
        // dimension, metric, ip, port
        String dataSource = kafkaServer.getName();

        List<String> dimensionList = Arrays.asList(query.getDimension().split(","));

        List<MetricsSpec> metricsSpecList = new ArrayList<>();
        metricsSpecList.add(new MetricsSpecCount());

        if (query != null && query.getMetric() != null) {
            List<String> metricList = Arrays.asList(query.getMetric().split(","));
            for (String s : metricList) {
                if (StringUtils.isNotBlank(s)) {
                    metricsSpecList.add(new MetricsSpecField(s, MetricsType.MIN));
                    metricsSpecList.add(new MetricsSpecField(s, MetricsType.MAX));
                    metricsSpecList.add(new MetricsSpecField(s, MetricsType.SUM));
                }
            }
        }

        String topic = kafkaServer.getName();

        String kafkaBroker = kafkaServer.getIp() + ":" + kafkaServer.getPort();

        IndexingOrderQuery indexingOrderQuery = new IndexingOrderQuery(dataSource, dimensionList, metricsSpecList, topic, kafkaBroker);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String requestBody = objectMapper.writeValueAsString(indexingOrderQuery);

            //bootstrapServers -> bootstrap.servers
            requestBody = requestBody.replace("bootstrapServers", "bootstrap.servers");

            indexingOrderConnector.requestIndexingOrder(overlordServer, requestBody);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
