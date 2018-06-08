package com.feon.springboot.common;

import com.alibaba.fastjson.JSONObject;

//@ApiModel("公共响应对象")
public class ApiResult {

//    @ApiModelProperty("错误码")
    private String errorCode = "0";
//    @ApiModelProperty("错误信息")
    private String errorMessage = "NO_ERROR";
//    @ApiModelProperty("响应对象")
    private JSONObject json;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }
}