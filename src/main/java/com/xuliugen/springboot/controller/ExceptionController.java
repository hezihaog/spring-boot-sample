package com.xuliugen.springboot.controller;

import com.xuliugen.springboot.common.ConstCommonString;
import com.xuliugen.springboot.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试异常处理的Controller
 * Created by xuliugen on 2017/3/27.
 */
@Controller
@RequestMapping(value = "/exception", produces = {ConstCommonString.APP_JSON_UTF_8})
public class ExceptionController {

    /**
     * 直接抛出异常
     */
    @ExceptionHandler(NotFoundException.class) //可以在方法级别上加，也可以直接加载Controller作为全局的
    @ResponseBody
    @RequestMapping(value = "/found", method = RequestMethod.GET)
    public void found() {
        throw new NotFoundException();
    }
}
