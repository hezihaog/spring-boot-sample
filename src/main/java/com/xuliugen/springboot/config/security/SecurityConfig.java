package com.xuliugen.springboot.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 启动Web安全性功能的最简单配置
 * Created by xuliugen on 2017/3/28.
 */
@Configuration
@EnableWebSecurity //启用Web安全性
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 通过重载，配置如何通过拦截器保护请求
     * 为不同的URL路径选择地应用安全性
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                formLogin().loginPage("/static/login.html")
                .and()
                .authorizeRequests()
                .antMatchers("/servlet/one").authenticated()
                .antMatchers(HttpMethod.POST, "/servlet/second").authenticated().anyRequest().permitAll()
                .and()
                .csrf().disable()
                .logout()
                .permitAll();
    }

    /**
     * 通过重载，配置user-detail服务
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /**
         * 启动你内存用户存储
         */
//        auth.inMemoryAuthentication()
//                .withUser("u  ser").password("password").roles("USER").and()
//                .withUser("admin").password("password").roles("ADMIN");

        auth.userDetailsService(new MyUserDetailsService());
    }

    /**
     * 通过重载，配置Spring Security的Filter链
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
