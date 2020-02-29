package com.example.hdlitest.aop.cglib;

import com.example.hdlitest.aop.UserManager;
import com.example.hdlitest.aop.impl.UserManagerImpl;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-3 15:48
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();

        UserManager o = (UserManager) cglibProxy.creatProxyedObj(UserManagerImpl.class);
        o.addUser("111","小明");
    }
}
