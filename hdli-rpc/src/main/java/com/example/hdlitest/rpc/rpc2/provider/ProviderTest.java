package com.example.hdlitest.rpc.rpc2.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-22 17:19
 */
public class ProviderTest {

    public static void main(String[] args) {

        ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfigP.class);
        ((AnnotationConfigApplicationContext) context).start();
    }
}
