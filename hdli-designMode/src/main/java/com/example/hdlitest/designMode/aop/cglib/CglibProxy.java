package com.example.hdlitest.designMode.aop.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-3 15:05
 */
public class CglibProxy implements MethodInterceptor {

    // 根据一个类型产生代理类，此方法不要求一定放在MethodInterceptor中
    public Object creatProxyedObj(Class<?> clazz){

        //代理类class文件存入本地磁盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");

        // 帮我们生成代理对象
        Enhancer enhancer = new Enhancer();
        // 设置对谁进行代理
        enhancer.setSuperclass(clazz);
        //回调函数
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     *
     * @param o                  cglib生成的代理对象
     * @param method             被代理的原始方法
     * @param objects            运行期的参数
     * @param methodProxy        产生的代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String methodName = method.getName();
        System.out.println("start-->>"+methodName);
        System.out.println("请求方法的参数:");
        if (objects != null){
            for (Object ob : objects) {
                System.out.println(ob);
            }
        }
        Object result = null;
        try {
            /*原对象方法调用前处理日志信息*/
            System.out.println("satrt-->>"+methodName);

            result = methodProxy.invokeSuper(o, objects);

            /*原对象方法调用后处理日志信息*/
            System.out.println("success-->>"+methodName);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error-->>"+methodName);
            throw e;
        }

        return result;
    }
}
