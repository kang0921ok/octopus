package osc.gobaby.octopus.support;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import osc.gobaby.octopus.service.admin.db.DbConnectUtils;
import osc.gobaby.octopus.service.admin.db.entity.DbConnect;

@Configuration
public class BeanConfig {
    @Bean
    public BasicDataSource basicDataSource() {
        if (DbConnectUtils.isCreatedDbConnectMetaFile()) {
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
}
