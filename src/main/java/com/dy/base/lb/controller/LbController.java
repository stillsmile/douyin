package com.dy.base.lb.controller;

import com.dy.base.lb.enums.ErrorEnum;
import com.dy.base.lb.response.JsonObjectResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LbController {

    @RequestMapping("/")
    public JsonObjectResponse DefaultMethod(){
        String resultMes = "DefaultMethod 请求，调用成功返回";
        return new JsonObjectResponse<>(resultMes);
    }

    @RequestMapping("/first")
    public JsonObjectResponse FirstMethod(){
        String resultMes = "first 请求，调用成功返回";
        JsonObjectResponse response = new JsonObjectResponse<>();
        response.setData(resultMes);
        response.setCode(ErrorEnum.RES_CODE_SUCCESS.getErrorCode());
        response.setErrmsg(ErrorEnum.RES_CODE_SUCCESS.getErrorMessage());
        return new JsonObjectResponse<>(resultMes);
    }

    @RequestMapping("/second")
    public JsonObjectResponse SecondMethod(){
        String resultMes = "second 请求，调用成功返回";
        return new JsonObjectResponse<>(resultMes);
    }
}
