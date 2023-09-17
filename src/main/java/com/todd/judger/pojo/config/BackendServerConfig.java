package com.todd.judger.pojo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "backend-server")
public class BackendServerConfig {
    private String address;
    private String port;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost(){
        return String.format("%s:%s", address, port);
    }

    public String getUrl(){
        return String.format("http://%s", getHost());
    }
}
