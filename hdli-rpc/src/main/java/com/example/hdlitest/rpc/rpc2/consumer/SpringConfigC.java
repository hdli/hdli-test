package com.example.hdlitest.rpc.rpc2.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lihuidong
 * @version 1.0
 * @date 2020/2/8 0:44
 */
@Configuration
public class SpringConfigC {

    @Bean(name="rpcPRoxyClient")
    public RpcProxyClient proxyClient(){
        return new RpcProxyClient();
    }
}
