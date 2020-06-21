package com.dy.base.lb.response;

import java.io.Serializable;

/**
 * 返回信息基础类
 *
 * @author: saiwei2
 * @date: 2020-6-21 18:02:55
 */
public class JsonBaseResponse implements Serializable {

    /**
     * 返回状态码: 0成功，-1失败
     */
    protected Integer code;

    /**
     * 操作结果信息
     */
    protected String errmsg;

    public JsonBaseResponse() {
        super();
    }

    public JsonBaseResponse(Integer code) {
        this.code = code;
    }

    public JsonBaseResponse(Integer code, String errmsg) {
        this.code = code;
        this.errmsg = errmsg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public JsonBaseResponse setErrmsg(String errmsg) {
        this.errmsg = errmsg;
        return this;
    }

    public Boolean success() {
        return this.code == 0;
    }
}