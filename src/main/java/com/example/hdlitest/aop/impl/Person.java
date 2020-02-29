package com.example.hdlitest.aop.impl;

import com.example.hdlitest.aop.BaseService;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-2 16:02
 */
public class Person implements BaseService {

    /**
     * //主要业务，代理模式要求开发人员只关心主要业务
     */
    @Override
    public void eat() {
        System.out.println("使用筷子吃饭....");
    }

    @Override
    public void wc() {
        System.out.println("测试地球重力是否存在");
    }
}
