package com.example.hdlitest.rpc.rpc1.provider;

import com.example.hdlitest.rpc.rpc1.api.CalculateService;
import com.example.hdlitest.rpc.rpc1.provider.impl.CalculateServiceImpl;

import java.io.IOException;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-3 21:48
 */
public class RpcProvider {

    public static void main(String[] args) throws IOException {
        CalculateService service =new CalculateServiceImpl();
        RpcFramework.publish(service,8888);
    }
}
