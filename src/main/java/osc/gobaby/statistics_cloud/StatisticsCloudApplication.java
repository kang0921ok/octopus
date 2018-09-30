package osc.gobaby.statistics_cloud;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import osc.gobaby.statistics_cloud.admin.db.DbConnectUtils;
import osc.gobaby.statistics_cloud.admin.db.entity.DbConnect;
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
        if(DbConnectUtils.isCreatedDbConnectMetaFile()){
            DbConnect dbConnect = DbConnectUtils.findDbConnectMetaFile();
            return DbConnectUtils.createBasicDataSource(dbConnect);
        } else {
            return new BasicDataSource();
        }
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(basicDataSource());
        return sessionFactoryBean.getObject();
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Configuration
    public class WebMvcConfig extends WebMvcConfigurerAdapter {

        @Autowired
        @Qualifier(value = "dbConnectionInterceptor")
        private HandlerInterceptor interceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(interceptor)
                    .addPathPatterns("/**")
                    .excludePathPatterns("/api/**")
                    .excludePathPatterns("/gate/**")
                    .excludePathPatterns("/resources/**");
        }
    }
}
