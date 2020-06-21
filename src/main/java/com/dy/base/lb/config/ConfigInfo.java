package com.dy.base.lb.config;

import com.dy.base.lb.util.DateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigInfo {

    @Value("${lb.version:未设置}")
    private String appVersion;

    @Value("${spring.application.name:未设置}")
    private String appName;

    @Value("${server.port:未设置}")
    private String port;

    @Value("${lb.config.cors.enable:未设置}")
    private String corsEnable;

    @Value("${spring.datasource.tomcat.initial-size:未设置}")
    private String tomcatInit;

    @Value("${spring.profiles.active:未设置}")
    private String active;

    @Override
    public String toString(){
        return  "\n*****************************************************" +
                "\n***       服务版本 : " + appVersion +
                "\n***       服务名称 : " + appName +
                "\n***       服务端口 : " + port +
                "\n***       白名单表 : " + corsEnable +
                "\n***       连接池数 : " + tomcatInit +
                "\n***       运行环境 : " + active +
                "\n******************************************************" +
                "\n" +
                "\n***   服务启动完毕 --> " + DateUtil.getTodayStr(DateUtil.DATE_STRING_FORMAT_ALL);
    }
}
