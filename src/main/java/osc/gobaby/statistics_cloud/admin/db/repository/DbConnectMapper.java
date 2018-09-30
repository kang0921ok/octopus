package osc.gobaby.statistics_cloud.admin.db.repository;


import osc.gobaby.statistics_cloud.support.BaseMapper;


/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
public interface DbConnectMapper extends BaseMapper {
    public int createDb();
    public int choiceDb();
    public int createUserTable();
    public int createServerTable();
    public int createQueryTable();
    public int insertAdminUser();
}
