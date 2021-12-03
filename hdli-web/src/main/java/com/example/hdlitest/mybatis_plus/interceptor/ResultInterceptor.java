package com.example.hdlitest.mybatis_plus.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-17 14:23
 */
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args={Statement.class})
})
@Component
@Slf4j
public class ResultInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.error("拦截器ResultInterceptor");
        // ResultSetHandler resultSetHandler1 = (ResultSetHandler) invocation.getTarget();
        //通过java反射获得mappedStatement属性值
        //可以获得mybatis里的resultype
        Object result = invocation.proceed();
        log.info("请求的结果："+result);
//        if (result instanceof ArrayList) {
//            ArrayList resultList = (ArrayList) result;
//            for (int i = 0; i < resultList.size(); i++) {
//                Object oi = resultList.get(i);
//                Class c = oi.getClass();
//                Class[] types = {String.class};
//                Method method = c.getMethod("setEmail", types);
//                // 调用obj对象的 method 方法
//                method.invoke(oi, "china");
//            }
//        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
