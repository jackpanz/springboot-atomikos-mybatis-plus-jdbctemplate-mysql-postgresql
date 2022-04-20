package com.softfabrique.test.conf.database.atomikos.mmsql;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MmSqlAtomikosDataBaseConf {

    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.mmsql")
    @Bean(name = "mmsqlAtomikosDataSource")
    public DataSource mmsqlAtomikosDataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "mmsqlAtomikosTemplate")
    public JdbcTemplate mmsqlAtomikosTemplate(@Qualifier("mmsqlAtomikosDataSource") DataSource mmsqlAtomikosDataSource) {
        return new JdbcTemplate(mmsqlAtomikosDataSource);
    }

}
