package com.softfabrique.test.conf;

import com.github.jackpanz.spring.beetl.ToEncode;
import com.github.jackpanz.spring.beetl.ToJson;
import com.github.jackpanz.spring.beetl.ToString;
import org.beetl.core.Function;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Configuration
public class BeetlConf {

    //@Value("${beetl.templatesPath:templates}")
    String templatesPath = "pages";

    //@Value("${beetl.suffix:btl}")
    String suffix = ".btl";

    @Value("${beetl.autoCheck:true}")
    String autoCheck;

    //@Value("${beetl.delimiterStatementStart:<%}")
    String delimiterStatementStart = "@";

    //@Value("${beetl.delimiterStatementEnd:<%}")
    String delimiterStatementEnd = "";

    @Bean
    public BeetlGroupUtilConfiguration beetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        Properties properties = new Properties();
        properties.setProperty("RESOURCE.autoCheck", autoCheck);
        properties.setProperty("DELIMITER_STATEMENT_START", delimiterStatementStart);
        properties.setProperty("DELIMITER_STATEMENT_END", delimiterStatementEnd);
//        properties.setProperty("RESOURCE.root",templatesPath);
        beetlGroupUtilConfiguration.setConfigProperties(properties);

        //获取Spring Boot 的ClassLoader
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = BeetlConf.class.getClassLoader();
        }

        beetlGroupUtilConfiguration.setFunctions(new HashMap<String, Function>() {{
            put("toJson", new ToJson());
            put("toEncode", new ToEncode());
            put("toString", new ToString());
        }});

        //beetlGroupUtilConfiguration.setConfigProperties();//额外的配置，可以覆盖默认配置，一般不需要
        ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader, templatesPath);
        beetlGroupUtilConfiguration.setResourceLoader(cploder);
        beetlGroupUtilConfiguration.init();
//        //如果使用了优化编译器，涉及到字节码操作，需要添加ClassLoader
        beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(loader);

        return beetlGroupUtilConfiguration;
    }

    @Bean
    public BeetlSpringViewResolver beetlSpringViewResolver(BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setSuffix(suffix);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        //解决nginx+tomcat重定向的问题
        beetlSpringViewResolver.setRedirectHttp10Compatible(false);
        return beetlSpringViewResolver;
    }

    @Bean(name = "groupTemplate")
    public GroupTemplate groupTemplate(BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        GroupTemplate groupTemplate = beetlGroupUtilConfiguration.getGroupTemplate();
        Map<String,Object> shared = new HashMap();
//        shared.put("IS_DEV", ConstantsConfigurer.IS_DEV);
//        shared.put("APP_NAME", ConstantsConfigurer.APP_NAME);
        groupTemplate.setSharedVars(shared);
        return groupTemplate;
    }

//
//    @Bean
//    public WebSimulate getWebSimulate(GroupTemplate groupTemplate, ObjectMapper objectMapper) {
//        return new WebSimulate(groupTemplate, new ObjectMapperJsonUtil(objectMapper));
//    }


}
