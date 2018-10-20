package osc.gobaby.octopus.service.indexing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.octopus.service.admin.server.AdminServerService;
import osc.gobaby.octopus.service.admin.server.entity.AdminServer;
import osc.gobaby.octopus.exception.NoMandatoryKeyException;
import osc.gobaby.octopus.service.indexing.entity.IndexingOrderQuery;
import osc.gobaby.octopus.service.indexing.entity.metrics.MetricsSpec;
import osc.gobaby.octopus.service.indexing.entity.metrics.MetricsSpecCount;
import osc.gobaby.octopus.service.indexing.entity.metrics.MetricsSpecField;
import osc.gobaby.octopus.service.indexing.entity.metrics.MetricsType;
import osc.gobaby.octopus.service.query.schema.QuerySchemaService;
import osc.gobaby.octopus.service.query.schema.entity.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IndexingOrderService {
    private static final Logger LOG = Logger.getLogger(IndexingOrderService.class);


    @Autowired
    private IndexingOrderConnector indexingOrderConnector;

    @Autowired
    private AdminServerService adminServerService;

    @Autowired
    private QuerySchemaService querySchemaService;

    public AdminServer createIndexingJob(String userId, String queryId) {
        //druid indexing
        Query query = querySchemaService.findQueryForUserIdAndQueryId(userId, queryId);
        if(query == null){
            return null;
        }

        AdminServer druidOverlordServer = adminServerService.findDruidOverlordServer();
        AdminServer kafkaServer = adminServerService.findKafkaServer();
        startIndexing(druidOverlordServer, kafkaServer, query);

        //query start indexing boolean update
        query.setStartIndexing(true);
        try {
            querySchemaService.modifyQuery(query);
        } catch (NoMandatoryKeyException e){

        }

        //kafka info return
        return kafkaServer;
    }

    public AdminServer deleteIndexingJob(String userId, String queryId) {
        //druid indexing
        Query query = querySchemaService.findQueryForUserIdAndQueryId(userId, queryId);
        if(query == null){
            return null;
        }

        AdminServer druidOverlordServer = adminServerService.findDruidOverlordServer();
        AdminServer kafkaServer = adminServerService.findKafkaServer();
        stopIndexing(druidOverlordServer, kafkaServer, query);

        //query start indexing boolean update
        query.setStartIndexing(false);
        try {
            querySchemaService.modifyQuery(query);
        } catch (NoMandatoryKeyException e){
            LOG.error(e);
        }

        //kafka info return
        return kafkaServer;
    }

    protected boolean startIndexing(AdminServer overlordServer, AdminServer kafkaServer, Query query) {
        // indexing service 요청
        // dimension, metric, ip, port
        String dataSource = query.getQueryName();

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

        String topic = query.getQueryName();

        String kafkaBroker = kafkaServer.getIp() + ":" + kafkaServer.getPort();

        IndexingOrderQuery indexingOrderQuery = new IndexingOrderQuery(dataSource, dimensionList, metricsSpecList, topic, kafkaBroker);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String requestBody = objectMapper.writeValueAsString(indexingOrderQuery);

            //bootstrapServers -> bootstrap.servers
            requestBody = requestBody.replace("bootstrapServers", "bootstrap.servers");

            indexingOrderConnector.requestCreateIndexingJob(overlordServer, requestBody);
        } catch (Exception e) {
            LOG.error(e);
            return false;
        }

        return true;
    }

    protected boolean stopIndexing(AdminServer overlordServer, AdminServer kafkaServer, Query query){
        try {
            indexingOrderConnector.requestDeleteIndexingJob(overlordServer, query.getQueryName());
        } catch (Exception e){
            LOG.error(e);
            return false;
        }
        return true;
    }
}
