package com.example.hdlitest.aop.proxy;

import com.example.hdlitest.aop.BaseService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-2 18:08
 */
@Deprecated
public class ProxyFactory {

    /**
     * JDK动态代理模式下，代理对象的数据类型
     * 应该由监控行为来描述
     * 参数： Class文件，监控类
     *
     * @param classFile
     * @return
     * @throws Exception
     */
    public static BaseService Builder(Class classFile) throws Exception {
        //1.创建被监控实例对象
        BaseService obj = (BaseService) classFile.newInstance();
        //2.创建一个通知对象 用接口来描述
        InvocationHandler adviser = new Invocation(obj);
        //3.向JVM申请负责监控obj对象指定行为的监控对象（代理对象）
        /*
         *  loader:被监控对象隶属的类文件在内存中真实地址
         *  interfaces:被监控对象隶属的类文件实现接口
         *  h：监控对象发现小明要执行被监控行为，应该有哪一个通知对象进行辅助
         */
        BaseService $proxy = (BaseService) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                adviser);
        return $proxy;
    }
}
