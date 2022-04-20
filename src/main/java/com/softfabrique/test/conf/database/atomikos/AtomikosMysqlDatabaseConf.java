package com.softfabrique.test.conf.database.atomikos;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class AtomikosMysqlDatabaseConf {

    @Primary
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.primary")
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        return new AtomikosDataSourceBean();
    }


    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource primaryDataSource) {
        return new JdbcTemplate(primaryDataSource);
    }

}

//        DataSource(DB2单数据库操作) -> transaction -> ServiceImpl 指定 Transaction
//
//        AtomikosDataSource(DB1单数据库操作) -> JtaTransactionManager -> ServiceImpl 指定 Transaction
//        AtomikosDataSource(DB2单数据库操作) -> JtaTransactionManager -> ServiceImpl 指定 Transaction


