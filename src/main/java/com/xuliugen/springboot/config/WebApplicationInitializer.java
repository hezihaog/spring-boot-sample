package com.xuliugen.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Spring MVC的DispatcherServlet配置
 * Created by xuliugen on 2017/3/26.
 */
@Configuration
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 创建ContextLoaderListener上下文
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * DispatchServlet创建Spring上下文，加载配置文件或者配置类中声明的Bean
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMVCConfig.class};
    }

    /**
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/**"};
    }

    /**
     * 重写这个方法，进行设置文件上传时候的参数
     * @param registration
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(
                /**
                 * 这里可以使用MultipartConfigElement的不同构造方法进行设置不同的参数设置
                 */
                new MultipartConfigElement("/files/", 20 * 1024, 40 * 1024, 0)
        );
    }
}
