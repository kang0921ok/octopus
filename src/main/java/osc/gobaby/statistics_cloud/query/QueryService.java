package osc.gobaby.statistics_cloud.query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.statistics_cloud.controller.exception.NoMandatoryKeyException;
import osc.gobaby.statistics_cloud.query.entity.Query;
import osc.gobaby.statistics_cloud.query.repository.QueryMapper;

import java.util.List;

@Service
public class QueryService {

    @Autowired
    private QueryMapper queryMapper;

    public boolean createQuery(Query query) {
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
