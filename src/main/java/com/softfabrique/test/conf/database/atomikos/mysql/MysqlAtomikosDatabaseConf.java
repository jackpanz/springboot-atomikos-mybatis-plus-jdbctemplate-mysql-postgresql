package com.softfabrique.test.conf.database.atomikos.mysql;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class MysqlAtomikosDatabaseConf {

    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.mysql")
    @Bean(name = "mysqlAtomikosDataSource")
    public DataSource mysqlAtomikosDataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "mysqlAtomikosTemplate")
    public JdbcTemplate mysqlAtomikosTemplate(@Qualifier("mysqlAtomikosDataSource") DataSource mysqlAtomikosDataSource) {
        return new JdbcTemplate(mysqlAtomikosDataSource);
    }

}


