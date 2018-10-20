package osc.gobaby.octopus.service.query.schema.repository;


import osc.gobaby.octopus.service.query.schema.entity.Query;
import osc.gobaby.octopus.support.BaseMapper;

import java.util.List;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
public interface QueryMapper extends BaseMapper {
    public Query selectOne(String userId);
    public List<Query> selectList();
    public List<Query> selectListForUserId(String userId);
    public int insert(Query query);
    public int update(Query query);
}
