package com.xuliugen.springboot.config;

import com.xuliugen.springboot.filter.SecondFilter;
import com.xuliugen.springboot.filter.ThirdFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 添加用户自定义的Filter
 * Created by xuliugen on 2017/3/27.
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean secondFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter(new SecondFilter());
        registration.addUrlPatterns("/*");
        registration.setName("SecondFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean thirdFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter(new ThirdFilter());
        registration.addUrlPatterns("/*");
        registration.setName("ThirdFilter");
        registration.setOrder(3);
        return registration;
    }

}
