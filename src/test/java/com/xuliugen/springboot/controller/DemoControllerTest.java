package com.xuliugen.springboot.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * 使用下边的几种方式去测试Controller
 * Created by xuliugen on 2017/3/26.
 */
public class DemoControllerTest {

    @Test
    public void testHome1() {
        DemoController controller = new DemoController();
        controller.home();
    }

    @Test
    public void testHome2() {
        DemoController controller = new DemoController();
        //进行断言，测试返回的数据是不是hello，返回结果为失败的
        Assert.assertEquals("hello", controller.home());
    }

    @Test
    public void testHome3() throws Exception {
        DemoController controller = new DemoController();
        //搭建MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/hello")).andExpect(view().name("index"));
    }
}
