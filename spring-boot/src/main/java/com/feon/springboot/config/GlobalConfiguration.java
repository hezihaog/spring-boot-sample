package com.feon.springboot.config;

import com.feon.springboot.interceptors.StudentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Package: com.feon.springboot.config
 * FileName: GlobalConfiguration
 * Date: on 2018/6/6  下午4:56
 * Auther: Wally
 * Descirbe:全局配置
 */
@Configuration
public class GlobalConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        //学生添加操作拦截器
        registry.addInterceptor(new StudentInterceptor()).addPathPatterns("/student/get");
        registry.addInterceptor(new StudentInterceptor()).addPathPatterns("/student/update");
        registry.addInterceptor(new StudentInterceptor()).addPathPatterns("/student/delete");
    }
}