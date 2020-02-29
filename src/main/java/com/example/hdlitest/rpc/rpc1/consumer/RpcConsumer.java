package com.example.hdlitest.rpc.rpc1.consumer;

import com.example.hdlitest.rpc.rpc1.api.CalculateService;
import com.example.hdlitest.rpc.rpc1.api.People;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-3 18:16
 */
public class RpcConsumer {
    public static void main(String[] args) {
        CalculateService service=RpcFramework.call(CalculateService.class,"127.0.0.1",8888);
        People people=new People(1,1);
        String hello=service.calculate(people);
        System.out.println(hello);
    }
}
