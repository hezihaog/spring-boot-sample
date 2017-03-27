package com.xuliugen.springboot.controller;

import com.xuliugen.springboot.common.ConstCommonString;
import com.xuliugen.springboot.common.LogUtil;
import com.xuliugen.springboot.domain.User;
import com.xuliugen.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by xuliugen on 2017/3/26.
 */
@Controller
public class UserController {

    transient final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add/{username}/{password}", method = RequestMethod.POST)
    public ModelAndView register(@PathVariable("username") String username, @PathVariable("password") String password) {
        MDC.put(ConstCommonString.TRACE_ID, LogUtil.getTraceId("USER_CONTROLLER_REGISTER"));
        logger.info("Request Params--username:" + username + "---password:" + password);

        int isSuccess = userService.register(username, password);
        ModelAndView modelAndView = new ModelAndView();
        if (isSuccess > 0) {
            modelAndView.setViewName("success");
        } else {
            modelAndView.setViewName("failed");
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> list() {
        MDC.put(ConstCommonString.TRACE_ID, LogUtil.getTraceId("USER_CONTROLLER_LIST"));
        logger.info("Request Params--");

        return userService.listUser();
    }
}
