package com.softfabrique.test.conf.database.atomikos.mysql;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.softfabrique.test.mapper.atomikos.mysql"}, sqlSessionTemplateRef = "mysqlAtomikosSessionTemplate")
public class MySqlAtomikosMybatisPlusConf {

    @Bean(name = "mysqlAtomikosSessionFactory")
    public SqlSessionFactory mysqlAtomikosSessionFactory(@Qualifier("mysqlAtomikosDataSource") DataSource mysqlAtomikosDataSource)
            throws Exception {

        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(mysqlAtomikosDataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.softfabrique.test.entity.mysql");

        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setCacheEnabled(false);
        mybatisConfiguration.setLogPrefix("sql.");
        mybatisConfiguration.setMapUnderscoreToCamelCase(false);

        //分页
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        mybatisConfiguration.addInterceptor(interceptor);

        sessionFactoryBean.setConfiguration(mybatisConfiguration);
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "mysqlAtomikosSessionTemplate")
    public SqlSessionTemplate mysqlAtomikosSessionTemplate(@Qualifier("mysqlAtomikosSessionFactory") SqlSessionFactory mysqlAtomikosSessionFactory) {
        return new SqlSessionTemplate(mysqlAtomikosSessionFactory);
    }

}
