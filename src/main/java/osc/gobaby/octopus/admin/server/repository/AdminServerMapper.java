package osc.gobaby.octopus.admin.server.repository;


import osc.gobaby.octopus.admin.server.entity.AdminServer;
import osc.gobaby.octopus.support.BaseMapper;

import java.util.List;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
public interface AdminServerMapper extends BaseMapper {
    public AdminServer selectOne(String userId);
    public List<AdminServer> selectList();
    public AdminServer selectKafkaType();
    public AdminServer selectDruidOverlordType();
    public AdminServer selectDruidBrokerType();
    public int insert(AdminServer adminServer);
    public int update(AdminServer adminServer);
}
