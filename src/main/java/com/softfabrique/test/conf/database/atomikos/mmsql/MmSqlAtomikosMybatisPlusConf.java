package com.softfabrique.test.conf.database.atomikos.mmsql;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
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

@Configuration
@MapperScan(basePackages = {"com.softfabrique.test.mapper.atomikos.mmsql"}, sqlSessionTemplateRef = "mmsqlAtomikosSessionTemplate")
public class MmSqlAtomikosMybatisPlusConf {

    @Bean(name = "mmsqlAtomikosSessionFactory")
    public SqlSessionFactory mmsqlAtomikosSessionFactory(@Qualifier("mmsqlAtomikosDataSource") DataSource mmsqlAtomikosDataSource)
            throws Exception {

        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(mmsqlAtomikosDataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.softfabrique.test.entity.mmsql");

        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setCacheEnabled(false);
        mybatisConfiguration.setLogPrefix("sql.");
        mybatisConfiguration.setMapUnderscoreToCamelCase(false);

        //分页
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.SQL_SERVER));
        mybatisConfiguration.addInterceptor(interceptor);

        sessionFactoryBean.setConfiguration(mybatisConfiguration);
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "mmsqlAtomikosSessionTemplate")
    public SqlSessionTemplate mmsqlAtomikosSessionTemplate(@Qualifier("mmsqlAtomikosSessionFactory") SqlSessionFactory mmsqlAtomikosSessionFactory) {
        return new SqlSessionTemplate(mmsqlAtomikosSessionFactory);
    }

}
