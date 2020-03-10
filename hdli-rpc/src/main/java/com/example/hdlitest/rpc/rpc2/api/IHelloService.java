package com.example.hdlitest.rpc.rpc2.api;

public interface IHelloService {

    String sayHello(String content);

    /**
     * 保存用户
     * @param user
     * @return
     */
    String saveUser(User user);
}
