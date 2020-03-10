package com.example.hdlitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Lihuidong
 * @version 1.0
 * @date 2020/3/10 11:14
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class HdliWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdliWebApplication.class,args);
    }
}
