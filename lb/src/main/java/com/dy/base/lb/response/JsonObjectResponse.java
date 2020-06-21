package com.dy.base.lb.response;

import com.dy.base.lb.enums.ErrorEnum;

/**
 * 返回数据对象
 *
 * @author: saiwei2
 * @date: 2020-6-21 18:02:55
 */
public class JsonObjectResponse<T> extends JsonBaseResponse {

    private T data;

    public JsonObjectResponse() {
        super();
    }

    public JsonObjectResponse(Integer code) {
        super(code);
    }

    public JsonObjectResponse(Integer code, String errmsg) {
        super(code, errmsg);
    }

    public JsonObjectResponse(Integer code, String errmsg, T data) {
        super(code, errmsg);
        this.data = data;
    }

    public JsonObjectResponse(ErrorEnum errorEnum) {
        this.code = errorEnum.getErrorCode();
        this.errmsg = errorEnum.getErrorMessage();
    }

    public JsonObjectResponse(ErrorEnum errorEnum, T data) {
        this.code = errorEnum.getErrorCode();
        this.errmsg = errorEnum.getErrorMessage();
        this.data = data;
    }

    public JsonObjectResponse(T data) {
        this(ErrorEnum.RES_CODE_SUCCESS, data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
