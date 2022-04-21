package com.softfabrique.test.conf.database.atomikos.postgresql;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 *  postgresql.conf
 *  max_prepared_transactions = 10
 */
@Configuration
public class PostgresqlAtomikosDatabaseConf {

    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.postgresql")
    @Bean(name = "postgresqlDataSource")
    public DataSource postgresqlDataSource() {
        return new AtomikosDataSourceBean();
    }


    @Bean(name = "postgresqlJdbcTemplate")
    public JdbcTemplate postgresqlJdbcTemplate(@Qualifier("postgresqlDataSource") DataSource postgresqlDataSource) {
        return new JdbcTemplate(postgresqlDataSource);
    }

}


