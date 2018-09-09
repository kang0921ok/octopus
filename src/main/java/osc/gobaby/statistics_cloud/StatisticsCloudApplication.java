package osc.gobaby.statistics_cloud;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import osc.gobaby.statistics_cloud.support.BaseMapper;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@SpringBootApplication
@MapperScan(basePackages = "osc.gobaby.statistics_cloud", markerInterface = BaseMapper.class, sqlSessionFactoryRef = "sqlSessionFactory")
public class StatisticsCloudApplication extends SpringBootServletInitializer {

    @Autowired
    private ApplicationContext applicationContext;


    public static void main(String[] args) {
        SpringApplication.run(StatisticsCloudApplication.class, args);
    }

    @Bean
    public BasicDataSource basicDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://kang0921ok.c69qhd8qewh9.ap-northeast-2.rds.amazonaws.com:3306/sc");
        basicDataSource.setUsername("kang0921ok");
        basicDataSource.setPassword("kangkang");
        return basicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(basicDataSource());
        return sessionFactoryBean.getObject();
    }
}
