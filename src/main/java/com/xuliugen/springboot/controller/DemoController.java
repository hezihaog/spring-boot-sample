package com.xuliugen.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xuliugen on 2017/3/26.
 */
@Controller
public class DemoController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        System.out.println("index----------!!!");
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String home() {
        System.out.println("home----------!!!");
        return "home";
    }
}
