package com.xuliugen.springboot.config;

import com.xuliugen.springboot.PathInterface;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * ContextLoaderListener Spring上下文
 * Created by xuliugen on 2017/3/26.
 */
@Configuration
@ComponentScan(basePackageClasses = {PathInterface.class},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
public class RootConfig {
}
