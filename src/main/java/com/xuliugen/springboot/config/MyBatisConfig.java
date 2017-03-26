package com.xuliugen.springboot.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.xuliugen.springboot.mapper.MapperPathInterface;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * MyBatis、Druid、Transaction
 * Created by xuliugen on 2017/3/26.
 */
@Configuration
@PropertySource("classpath:application.properties")
@MapperScan(basePackageClasses = {MapperPathInterface.class})
public class MyBatisConfig {

    @Autowired
    private Environment environment;

    /**
     * 创建数据源
     * @Primary 表示优先级，该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     */
    @Bean
    public DataSource getDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("spring.datasource.driver"));
        props.put("url", environment.getProperty("spring.datasource.url"));
        props.put("username", environment.getProperty("spring.datasource.username"));
        props.put("password", environment.getProperty("spring.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);//指定数据源(这个必须有，否则报错)
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(environment.getProperty("mybatis.mapperLocations")));//指定xml文件位置

        return factory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) throws Exception {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
