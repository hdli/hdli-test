package com.example.hdlitest.rpc.rpc2.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lihuidong
 * @version 1.0
 * @date 2020/2/8 0:37
 */
@Configuration
//@ComponentScan(basePackages = "com.example.hdlitest.rpc.rpc2.provider")
public class SpringConfigP {

    @Bean(name="rpcServerSpring")
    public RpcServerSpring gpRpcServer(){
        return new RpcServerSpring(8080);
    }
}
