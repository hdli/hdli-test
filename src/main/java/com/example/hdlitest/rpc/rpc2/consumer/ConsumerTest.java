package com.example.hdlitest.rpc.rpc2.consumer;

import com.example.hdlitest.rpc.rpc2.api.IHelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-22 17:18
 */
public class ConsumerTest {

    public static void main(String[] args) {
        IHelloService iHelloService = RpcProxyClient.clientProxy(IHelloService.class,"localhost",8080);
        String result=iHelloService.sayHello("Mic");
        System.out.println(result);

    }
}
