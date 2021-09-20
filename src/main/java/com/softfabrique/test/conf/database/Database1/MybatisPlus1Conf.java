package com.softfabrique.test.conf.database.Database1;//package com.softfabrique.test.conf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
@MapperScan(basePackages = {"com.softfabrique.test.mapper.database1"}, sqlSessionTemplateRef = "sqlSessionTemplateCar")
public class MybatisPlus1Conf {

    @Autowired
    MybatisPlusProperties properties;

    @Bean(name = "sqlSessionFactoryCar")
    public SqlSessionFactory sqlSessionFactoryCar(@Qualifier("primaryDataSource") DataSource primaryDataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(primaryDataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.softfabrique.test.entity.database1");

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
        GlobalConfig globalConfig = this.properties.getGlobalConfig();
        globalConfig.getDbConfig().setKeyGenerators(keyGenerators);

        sessionFactoryBean.setConfiguration(mybatisConfiguration);
        sessionFactoryBean.setGlobalConfig(globalConfig);
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplateCar")
    public SqlSessionTemplate sqlSessionTemplateCar(
            @Qualifier("sqlSessionFactoryCar") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
