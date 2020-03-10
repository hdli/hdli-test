package com.example.hdlitest.designMode.aop.impl;


import com.example.hdlitest.designMode.aop.UserManager;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-3 11:16
 */
public class UserManagerImpl implements UserManager {

    @Override
    public void addUser(String userId, String userName) {
        System.out.println("UserManagerImpl.addUser");
    }

    @Override
    public void delUser(String userId) {
        System.out.println("UserManagerImpl.delUser");
    }

    @Override
    public String findUser(String userId) {
        System.out.println("UserManagerImpl.findUser");
        return "张三";
    }

    @Override
    public void modifyUser(String userId, String userName) {
        System.out.println("UserManagerImpl.modifyUser");
    }
}
