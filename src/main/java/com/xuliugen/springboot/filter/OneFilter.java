package com.xuliugen.springboot.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 登录过滤器,可以通过@WebFilter(filterName = "OneFilter", urlPatterns = "/*")设置
 * 这样的话就不需要在FilterConfig中进行设置了
 * Created by xuliugen on 2017/3/27.
 */
@WebFilter(filterName = "OneFilter", urlPatterns = "/*")
public class OneFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
