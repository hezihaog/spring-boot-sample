package com.xuliugen.springboot.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * AbstractSecurityWebApplicationInitializer实现了WebApplicationInitializer，
 * Spring使用AbstractSecurityWebApplicationInitializer在Web容器中注册 @DelegatingFilterProxy
 * <p>
 * 创建一个名为springSecurityFilterChain的Filter Bean，DelegatingFilterProxy会将过滤逻辑委托给它。
 * Created by xuliugen on 2017/3/28.
 */
@Configuration
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {

}
