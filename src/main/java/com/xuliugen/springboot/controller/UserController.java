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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by xuliugen on 2017/3/26.
 */
@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 使用PathVariable的方式提交数据
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/add/{username}/{password}", method = RequestMethod.POST)
    public ModelAndView register(@PathVariable("username") @Valid String username,
                                 @PathVariable("password") @Valid String password) {
        MDC.put(ConstCommonString.TRACE_ID, LogUtil.getTraceId("USER_CONTROLLER_REGISTER"));
        logger.info("Request Params--username:" + username + "---password:" + password);

        ModelAndView modelAndView = new ModelAndView();
        int isSuccess = userService.register(username, password);

        if (isSuccess > 0) {
            modelAndView.setViewName("success");
        } else {
            modelAndView.setViewName("failed");
        }
        return modelAndView;
    }

    /**
     * 用于测试JSR-303校验
     * <p>
     * 加@Valid注解是使用的JSR-303校验规则,告知Spring确保这个对象满足校验限制
     * BindingResult result 表示校验规则出错的错误
     * <p>
     * 用户名,5-12位;密码,5-12位
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerByUser(@Valid User user, BindingResult result) {
        MDC.put(ConstCommonString.TRACE_ID, LogUtil.getTraceId("USER_CONTROLLER_REGISTER"));
        logger.info("Request Params--username:" + user.getUsername() + "---password:" + user.getPassword());

        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) { //如果检验出现错误,则返回错误信息
            modelAndView.addObject(new String("参数错误"));
            modelAndView.setViewName("failed");
        }
        int isSuccess = userService.register(user.getUsername(), user.getPassword());

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
