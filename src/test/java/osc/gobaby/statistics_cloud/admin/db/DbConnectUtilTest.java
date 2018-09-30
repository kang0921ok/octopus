package osc.gobaby.statistics_cloud.admin.db;

import org.junit.Before;
import org.junit.Test;
import osc.gobaby.statistics_cloud.admin.db.entity.DbConnect;
import osc.gobaby.statistics_cloud.admin.db.entity.DbType;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DbConnectUtilTest {

    private DbConnectUtils uut;

    @Before
    public void before() {
        uut = new DbConnectUtils();
    }

    @Test
    public void createDbMetaTest() {
        // given
        DbConnect dbConnect = new DbConnect();
        dbConnect.setDbType(DbType.MYSQL);
        dbConnect.setIp("kang0921ok.c69qhd8qewh9.ap-northeast-2.rds.amazonaws.com");
        dbConnect.setPort("3306");
        dbConnect.setUserName("kang0921ok");
        dbConnect.setPassword("kangkang");

        // when
        uut.createDbConnectMetaFile(dbConnect);

        DbConnect actual = uut.findDbConnectMetaFile();

        // then
        assertThat(actual.getUserName(), is(dbConnect.getUserName()));
    }
}