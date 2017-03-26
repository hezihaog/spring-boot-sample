package com.xuliugen.springboot.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by xuliugen on 2017/3/26.
 */
public class HomeControllerTest {

    @Test
    public void testHome1() {
        HomeController controller = new HomeController();
        controller.home();

    }

    @Test
    public void testHome2() {
        HomeController controller = new HomeController();
        //进行断言，测试返回的数据是不是hello
        Assert.assertEquals("hello", controller.home());
    }

    @Test
    public void testHome3() throws Exception {
        HomeController controller = new HomeController();
        //搭建MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/hello")).andExpect(view().name("index"));
    }
}
