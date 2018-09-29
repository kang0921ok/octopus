package osc.gobaby.statistics_cloud.admin.server.repository;


import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;
import osc.gobaby.statistics_cloud.support.BaseMapper;
import osc.gobaby.statistics_cloud.user.entity.User;

import java.util.List;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
public interface AdminServerMapper extends BaseMapper {
    public AdminServer selectOne(String userId);
    public List<AdminServer> selectList();
    public int insert(AdminServer adminServer);
    public int update(AdminServer adminServer);
}
