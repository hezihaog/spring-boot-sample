package com.feon.springboot.interceptors;

import com.alibaba.fastjson.JSON;
import com.feon.springboot.common.BaseResult;
import com.feon.springboot.model.vo.StudentVO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: com.feon.springboot.interceptor
 * FileName: StudentInterceptor
 * Date: on 2018/6/6  下午4:46
 * Auther: Wally
 * Descirbe:学生表拦截器
 */
public class StudentInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String studentId = request.getParameter("studentId");
        if (studentId == null || studentId.replaceAll(" ", "").equals("")) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            BaseResult<StudentVO> result = new BaseResult<StudentVO>(-1, new String("请求的studentId不存在".getBytes("UTF-8")), null);
            response.getWriter().write(JSON.toJSONString(result));
            return false;
        } else {
            return true;
        }
    }
}