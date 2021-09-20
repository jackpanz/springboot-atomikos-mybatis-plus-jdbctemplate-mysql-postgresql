package com.softfabrique.test.conf.database.Database2;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Aspect
@Configuration
public class Database2Conf {

    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.second")
    @Bean(name = "dataSourceSecond")
    public DataSource dataSourceSecond() {
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "jdbcTemplateSecond")
    public JdbcTemplate jdbcTemplateSecond(@Qualifier("dataSourceSecond") DataSource dataSourceSecond) {
        return new JdbcTemplate(dataSourceSecond);
    }


}
