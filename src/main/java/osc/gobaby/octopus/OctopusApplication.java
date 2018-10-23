package osc.gobaby.octopus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import osc.gobaby.octopus.support.BaseMapper;


/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@SpringBootApplication
@MapperScan(basePackages = "osc.gobaby.octopus", markerInterface = BaseMapper.class, sqlSessionFactoryRef = "sqlSessionFactory")
public class OctopusApplication extends SpringBootServletInitializer {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(OctopusApplication.class, args);
    }
}
