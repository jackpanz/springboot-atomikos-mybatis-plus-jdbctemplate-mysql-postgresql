package com.softfabrique.test.conf.database.Database2;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.softfabrique.test.mapper.database2"}, sqlSessionTemplateRef = "sqlSessionTemplateSecond")
public class MybatisPlus2Conf {

    @Autowired
    MybatisPlusProperties properties;

    @Bean(name = "sqlSessionFactorySecond")
    public SqlSessionFactory sqlSessionFactorySecond(@Qualifier("dataSourceSecond") DataSource dataSourceSecond)
            throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSourceSecond);
        sessionFactoryBean.setTypeAliasesPackage("com.softfabrique.test.entity.database2");

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

    @Bean(name = "sqlSessionTemplateSecond")
    public SqlSessionTemplate sqlSessionTemplateSecond(
            @Qualifier("sqlSessionFactorySecond") SqlSessionFactory sqlSessionFactorySecond){
        return new SqlSessionTemplate(sqlSessionFactorySecond);
    }


}
