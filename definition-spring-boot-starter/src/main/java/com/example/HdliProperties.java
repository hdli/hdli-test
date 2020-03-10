package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-20 11:12
 */
@ConfigurationProperties(prefix = "spring.hdli")
public class HdliProperties {

    private String name = "Jack";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
