package com.example;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-20 11:10
 */
public class HelloImpl implements Hello {
    @Autowired
    private HdliProperties hdliProperties;
    @Override
    public void welcome() {
        String name = hdliProperties.getName();
        System.out.println(name + "欢迎来到HDLI测试starter包");
    }
}
