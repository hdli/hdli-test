package com.example.hdlitest.designMode.aop.proxy;


import com.example.hdlitest.designMode.aop.BaseService;
import com.example.hdlitest.designMode.aop.UserManager;
import com.example.hdlitest.designMode.aop.impl.UserManagerImpl;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-2 18:11
 */
public class ProxyTest {


    /**
     * JDK动态代理的一般步骤如下：
     *
     * 1、创建被代理的接口和类；
     *
     * 2、实现InvocationHandler接口，对目标接口中声明的所有方法进行统一处理；
     *
     * 3、调用Proxy的静态方法，创建代理类并生成相应的代理对象；
     *
     * 4、使用代理。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        LogHandlerProxy logHandler=new LogHandlerProxy();
        UserManager proxyInstance = (UserManager) logHandler.newProxyInstance(new UserManagerImpl());
        proxyInstance.addUser("1111", "张三");

//        BaseService o = (BaseService) logHandler.newProxyInstance(new Person());
//        o.eat();

        /**
         * 生成代理类放在指定位置
         */
        byte[] bytes = ProxyGenerator.generateProxyClass("aaa", new Class[]{BaseService.class});
        FileOutputStream os = new FileOutputStream("E://aaa.class");
        os.write(bytes);
        os.close();
    }
}
