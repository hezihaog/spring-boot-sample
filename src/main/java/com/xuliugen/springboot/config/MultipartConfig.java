package com.xuliugen.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 * Created by xuliugen on 2017/3/27.
 */
@Configuration
public class MultipartConfig {

    /**
     * DispatcherServlet并没有实现任何接卸multipart请求数据的功能。
     * 它将该任务委托给Spring中MultipartResolver策略接口的实现，通过这个实现类来解析multipart请求的中的数据。
     * <p>
     * Spring  3.1之后的版本中，Spring内置了两个MultipartResolver的实现可供选择：
     * 1、CommonsMultipartResolver：Servlet在版本3.0之前可以使用该种方式；
     * 2、StandardServletMultipartResolver：Servlet 3.0版本之后推荐使用的方式；
     * @return
     */
    @Bean
    public MultipartResolver mutliPartResolver() {
        return new StandardServletMultipartResolver();
    }

    /**
     *需要注意的是：
     * 使用StandardServletMultipartResolver的方式，当我们需要设置文件上传的路径的时候，我们需要重写
     * AbstractAnnotationConfigDispatcherServletInitializer中的customizeRegistration方法进行设置文件上传的参数
     */
}
