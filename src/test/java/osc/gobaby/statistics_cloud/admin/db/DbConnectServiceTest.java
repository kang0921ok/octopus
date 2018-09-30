package osc.gobaby.statistics_cloud.admin.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import osc.gobaby.statistics_cloud.admin.db.entity.DbConnect;
import osc.gobaby.statistics_cloud.admin.db.entity.DbType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbConnectServiceTest {

    @Autowired
    private DbConnectService dbConnectService;

    @Test
    public void initTest(){
        // given
        DbConnect dbConnect = new DbConnect();
        dbConnect.setDbType(DbType.MYSQL);
        dbConnect.setIp("kang0921ok.c69qhd8qewh9.ap-northeast-2.rds.amazonaws.com");
        dbConnect.setPort("3306");
        dbConnect.setUserName("kang0921ok");
        dbConnect.setPassword("kangkang");

        // when
        dbConnectService.init(dbConnect);

        // then
    }

}