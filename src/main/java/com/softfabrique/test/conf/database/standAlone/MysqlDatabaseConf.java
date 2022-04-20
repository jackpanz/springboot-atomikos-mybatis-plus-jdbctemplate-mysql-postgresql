package com.softfabrique.test.conf.database.standAlone;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Aspect
@Configuration
public class MysqlDatabaseConf {

    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {
        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }


    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
        return new JdbcTemplate(mysqlDataSource);
    }

    @Bean(name = "mysqlTransaction")
    public DataSourceTransactionManager mysqlDataSourceTransactionManager(@Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
        return new DataSourceTransactionManager(mysqlDataSource);
    }

}