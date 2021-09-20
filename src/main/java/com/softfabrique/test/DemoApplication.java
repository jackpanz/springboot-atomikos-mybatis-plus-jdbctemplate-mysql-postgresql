package com.softfabrique.test;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.github.jackpanz.spring.exception.MExceptionHandler;
import com.github.jackpanz.spring.spring.converter.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@Configuration
@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MybatisPlusAutoConfiguration.class})})
public class DemoApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

    /**
     * 自定义数据转换类
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
        registry.addConverter(new BooleanConverter());
        registry.addConverter(new TimeConverter());
        registry.addConverter(new TimestampConverter());
//        解决bean的属性和上传文件同名时，会出现转换错误
        registry.addConverter(new MultipartFileToStringConverter());
    }

    /**
     * 统一错误处理
     *
     * @return
     */
    @Bean
    public ExceptionHandlerExceptionResolver exceptionHandler() {
        ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new MExceptionHandler();
        exceptionHandlerExceptionResolver.setOrder(0);
        return exceptionHandlerExceptionResolver;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
