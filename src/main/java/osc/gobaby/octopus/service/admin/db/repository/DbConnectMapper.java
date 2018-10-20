package osc.gobaby.octopus.service.admin.db.repository;


import osc.gobaby.octopus.support.BaseMapper;


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
