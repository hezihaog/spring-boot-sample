package com.feon.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.feon.springboot.common.BaseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//@Api(description = "提供APP端的REST接口", tags = "REST聚合服务")
@RestController
@RequestMapping("/rest")
public class RestfulController {

//    @ApiOperation("Restful接口测试")
    @PostMapping("/restful")
    public BaseResult restful(@RequestParam String name,
                              @RequestParam String hobby,
                              @RequestParam MultipartFile file) {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("hobby", hobby);
        json.put("file", file);
        return new BaseResult<>(json);
    }
}
