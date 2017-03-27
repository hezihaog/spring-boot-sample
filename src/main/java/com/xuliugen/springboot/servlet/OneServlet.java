package com.xuliugen.springboot.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet提供了@WebServlet注解，将Servlet进行映射,但是需要在主程序上加@ServletComponentScan注解
 * Created by xuliugen on 2017/3/27.
 */
@WebServlet(name = "OneServlet", urlPatterns = "/servlet/one")
public class OneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        System.out.println("-----------------" + name);
    }
}
