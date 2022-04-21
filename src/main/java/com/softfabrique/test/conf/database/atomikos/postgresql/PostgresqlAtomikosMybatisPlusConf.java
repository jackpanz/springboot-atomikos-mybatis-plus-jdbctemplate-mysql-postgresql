package com.softfabrique.test.conf.database.atomikos.postgresql;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan(basePackages = {"com.softfabrique.test.mapper.atomikos.postgresql"}, sqlSessionTemplateRef = "postgresqlSessionTemplate")
public class PostgresqlAtomikosMybatisPlusConf {

    @Autowired
    MybatisPlusProperties properties;

    @Bean(name = "postgresqlSessionFactory")
    public SqlSessionFactory postgresqlSessionFactory(@Qualifier("postgresqlDataSource") DataSource postgresqlDataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(postgresqlDataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.softfabrique.test.entity.postgresql");

        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setCacheEnabled(false);
        mybatisConfiguration.setLogPrefix("sql.");
        mybatisConfiguration.setMapUnderscoreToCamelCase(false);

        //分页
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
        mybatisConfiguration.addInterceptor(interceptor);

        List<IKeyGenerator> keyGenerators = new ArrayList<>();
        keyGenerators.add(new PostgreKeyGenerator());

        //id生成
        GlobalConfig globalConfig = properties.getGlobalConfig();
        globalConfig.getDbConfig().setKeyGenerators(keyGenerators);

        sessionFactoryBean.setConfiguration(mybatisConfiguration);
        sessionFactoryBean.setGlobalConfig(globalConfig);
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "postgresqlSessionTemplate")
    public SqlSessionTemplate postgresqlSessionTemplate(
            @Qualifier("postgresqlSessionFactory") SqlSessionFactory postgresqlSessionFactory){
        return new SqlSessionTemplate(postgresqlSessionFactory);
    }
}
