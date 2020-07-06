package com.cn.uk.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "getchildstation")
public class ChildReadConfig {

    private String ip;
    private int port;
    private String test_url;



    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getTest_url() {
        return test_url;
    }

    public void setTest_url(String test_url) {
        this.test_url = test_url;
    }
}
