package com.xuliugen.springboot.config;

import com.xuliugen.springboot.servlet.SecondServlet;
import com.xuliugen.springboot.servlet.ThirdServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在spring boot中添加自己的Servlet有两种方法，代码注册Servlet和注解自动注册（Filter和Listener也是如此）。
 * 一、代码注册通过ServletRegistrationBean、 FilterRegistrationBean 和 ServletListenerRegistrationBean 获得控制。
 * 也可以通过实现 ServletContextInitializer 接口直接注册。
 * <p>
 * 二、在 SpringBootApplication 上使用@ServletComponentScan 注解后，Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener
 * 注解自动注册，无需其他代码。
 * <p>
 * 三、WebApplicationInitializer下的onStartup方法
 * Created by xuliugen on 2017/3/27.
 */
@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean secondServlet() {
        // ServletName默认值为首字母小写，即myServlet
        return new ServletRegistrationBean(new SecondServlet(), "/servlet/second");
    }

    @Bean
    public ServletRegistrationBean thirdServlet() {
        // ServletName默认值为首字母小写，即myServlet
        return new ServletRegistrationBean(new ThirdServlet(), "/servlet/third");
    }

}
