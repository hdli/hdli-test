package com.example.hdlitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class HdliTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdliTestApplication.class, args);
    }

}
