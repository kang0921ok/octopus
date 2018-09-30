package osc.gobaby.statistics_cloud.query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.statistics_cloud.admin.server.AdminServerService;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;
import osc.gobaby.statistics_cloud.controller.exception.NoMandatoryKeyException;
import osc.gobaby.statistics_cloud.indexing.IndexingOrderService;
import osc.gobaby.statistics_cloud.query.entity.Query;
import osc.gobaby.statistics_cloud.query.repository.QueryMapper;

import java.util.List;

@Service
public class QueryService {

    @Autowired
    private QueryMapper queryMapper;

    @Autowired
    private AdminServerService adminServerService;

    @Autowired
    private IndexingOrderService indexingOrderService;

    public AdminServer startNewQuery(Query query){
        if(createQuery(query)){
            //druid indexing
            AdminServer druidOverlordServer = adminServerService.findDruidOverlordServer();
            AdminServer kafkaServer = adminServerService.findKafkaServer();
            indexingOrderService.indexingOrder(druidOverlordServer, kafkaServer, query);

            //kafka info return
            return kafkaServer;
        }

        return null;
    }

    protected boolean createQuery(Query query) {
        int insertNum = queryMapper.insert(query);

        return insertNum == 1 ? true : false;
    }

    public boolean modifyQuery(Query query) throws NoMandatoryKeyException {
        if(StringUtils.isBlank(query.getId())){
            throw new NoMandatoryKeyException("id");
        }

        int modifyNum = queryMapper.update(query);

        return modifyNum == 1 ? true : false;
    }

    public List<Query> findQueryList() {
        return queryMapper.selectList();
    }

    public List<Query> findQueryListForUserId(String userId) {
        return queryMapper.selectListForUserId(userId);
    }
}
