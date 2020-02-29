package com.example.hdlitest.rpc.rpc2.provider.impl;

import com.example.hdlitest.rpc.rpc2.api.IHelloService;
import com.example.hdlitest.rpc.rpc2.api.User;
import com.example.hdlitest.rpc.rpc2.provider.annontation.RpcService;

/**
 * @author Lihuidong
 * @version 1.0
 * @date 2020/2/7 23:51
 */
@RpcService(value = IHelloService.class,version = "v2.0")
public class HelloServiceImpl2 implements IHelloService {
    @Override
    public String sayHello(String content) {
        System.out.println("【v2.0】request in sayHello:"+content);
        return "【v2.0】Say Hello:"+content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("【V2.0】request in saveUser:"+user.toString());
        return "【v2.0】SUCCESS";
    }
}
