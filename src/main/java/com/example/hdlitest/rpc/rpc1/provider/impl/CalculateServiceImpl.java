package com.example.hdlitest.rpc.rpc1.provider.impl;

import com.example.hdlitest.rpc.rpc1.api.CalculateService;
import com.example.hdlitest.rpc.rpc1.api.People;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-3 18:34
 */
public class CalculateServiceImpl implements CalculateService {
    @Override
    public String calculate(People people) {
        System.out.println("调用Calculate方法");
        int res=people.getA()+people.getB();
        return "计算结果 "+res;
    }
}
