package com.example.hdlitest.spring_aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-13 16:00
 */
public class TracingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation i) throws Throwable {

        System.out.println("method "+i.getMethod()+" is called on "+
                i.getThis()+" with args "+i.getArguments());
        Object ret=i.proceed();
        System.out.println("method "+i.getMethod()+" returns "+ret);
        return ret;
    }
}
