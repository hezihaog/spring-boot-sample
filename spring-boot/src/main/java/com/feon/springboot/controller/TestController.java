package com.feon.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.feon.springboot.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    private static Logger LOG = LoggerFactory.getLogger(TestController.class);

    /**
     * 三种获取配置的方式，
     * 1：使用Environment对象
     * 2：使用@Value注解
     * 3：声明一个配置类，通过@ConfigurationProperties注解配置前缀
     */

    @Autowired
    private Environment env;
//    @Autowired
//    private ServerConfig serverConfig;

    @GetMapping("/hello")
    public JSONObject hello(@Value("${server.servlet.context-path}") String contextPath) {

        LOG.error("invoke the {} method", "hello");

        JSONObject json = new JSONObject();
        json.put("dir", env.getProperty("user.dir"));
        json.put("home", env.getProperty("user.home"));
        json.put("language", env.getProperty("user.language"));
        json.put("javaHome", env.getProperty("JAVA_HOME"));
//        json.put("port", env.getProperty("server.port", Integer.class));
//        json.put("port", serverConfig.getPort());
        json.put("contextPath", contextPath);
        return json;
    }

    /**
     * POST接口测试
     *
     * @param id   主键
     * @param name 姓名
     * @param age  年龄
     * @return
     */
    @PostMapping("/rest")
    public JSONObject rest(
            Integer id,
            String name,
            Integer age,
            String paramDTO) {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("age", age);
        JSONObject jsonObj = (JSONObject) JSONObject.parse(paramDTO);
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            json.put(entry.getKey(), entry.getValue());
        }
        return json;
    }
}
