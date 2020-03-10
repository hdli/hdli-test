package com.example.hdlitest.rpc.rpc2.provider.impl;

import com.example.hdlitest.rpc.rpc2.api.IHelloService;
import com.example.hdlitest.rpc.rpc2.api.User;
import com.example.hdlitest.rpc.rpc2.provider.annontation.RpcService;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-22 15:37
 */
@RpcService(value = IHelloService.class,version = "v1.0")
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String content) {
        System.out.println("【v1.0】request in sayHello:"+content);
        return "【v1.0】Say Hello:"+content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("【v1.0】request in saveUser:"+user.toString());
        return "【v1.0】SUCCESS";
    }
}
