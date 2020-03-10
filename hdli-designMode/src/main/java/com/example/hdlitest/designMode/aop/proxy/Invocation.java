package com.example.hdlitest.designMode.aop.proxy;



import com.example.hdlitest.designMode.aop.BaseService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-2 16:04
 */
@Deprecated
public class Invocation implements InvocationHandler {
    /**
     * 具体被监控对象
     */
    private BaseService obj;

    public Invocation(BaseService obj) {
        this.obj = obj;
    }

    /**
     * invoke方法：在被监控行为将要执行时，会被JVM拦截
     *            被监控行为和行为实现方会被作为参数输送invoke
     *            ***
     *            通知JVM,这个被拦截方法是如何与当前次要业务方法绑定实现
     * @param proxy   代理类实例
     * @param method  被调用的方法对象
     * @param params  调用参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
        //0.局部变量，接受主要业务方法执行完毕后返回值
        Object value;
        //1.确认当前被拦截行为
        String methodName= method.getName();
        //2.根据被拦截行为不同，决定主要业务和次要业务如何绑定执行
        if("eat".equals(methodName)){
            //饭前要洗手
            wash();
            //吃饭
            value = method.invoke(this.obj,params);
        } else {
            //便后要洗手
            value=method.invoke(this.obj, params);
            wash();
        }
        return value;
    }

    //次要业务
    public void wash(){
        System.out.println("-----洗手----");
    }
}
