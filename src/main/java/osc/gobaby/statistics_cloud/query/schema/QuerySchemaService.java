package osc.gobaby.statistics_cloud.query.schema;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.statistics_cloud.controller.exception.NoMandatoryKeyException;
import osc.gobaby.statistics_cloud.query.schema.entity.Query;
import osc.gobaby.statistics_cloud.query.schema.repository.QueryMapper;

import java.util.List;

@Service
public class QuerySchemaService {

    @Autowired
    private QueryMapper queryMapper;

    public boolean upsertQuery(Query query) throws NoMandatoryKeyException {
        List<Query> queryList = queryMapper.selectListForUserId(query.getUserId());
        if (queryList == null || queryList.size() == 0) {
            createQuery(query);
        } else {
            if (queryList.size() == 1) {
                //한개일떄는 queryName안보고도 변경 가능
                query.setId(queryList.get(0).getId());
                modifyQuery(query);
            } else {
                //한개 이상 시에는 쿼리네임이 같은걸 업데이트
                for (Query q : queryList) {
                    if (q.getQueryName().equals(query.getQueryName())) {
                        query.setId(q.getId());
                        break;
                    }
                }

                if (StringUtils.isBlank(query.getId())) {
                    //만약 같은 쿼리네임이 없으면 신규로 다시 만든다.
                    createQuery(query);
                } else {
                    modifyQuery(query);
                }
            }
        }
        return true;
    }

    protected boolean createQuery(Query query) {
        int insertNum = queryMapper.insert(query);

        return insertNum == 1 ? true : false;
    }

    public boolean modifyQuery(Query query) throws NoMandatoryKeyException {
        if (StringUtils.isBlank(query.getId())) {
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

    public Query findQueryForUserIdAndQueryName(String userId, String queryName) {
        List<Query> queryList =  queryMapper.selectListForUserId(userId);
        for(Query query : queryList){
            if(query.getQueryName().equals(queryName)){
                return query;
            }
        }

        return null;
    }

    public Query findQueryForUserIdAndQueryId(String userId, String queryId) {
        List<Query> queryList =  queryMapper.selectListForUserId(userId);
        for(Query query : queryList){
            if(query.getId().equals(queryId)){
                return query;
            }
        }

        return null;
    }
}
