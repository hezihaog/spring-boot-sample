package com.feon.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class SpringSessionController {

    @GetMapping("/currSession")
    public JSONObject currSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        JSONObject json = new JSONObject();
        json.put("serverPort", req.getLocalPort());
        json.put("sessionClass", session.getClass());
        json.put("sessionId", session.getId());
        System.out.println(session.getClass());
        return json;
    }
}
