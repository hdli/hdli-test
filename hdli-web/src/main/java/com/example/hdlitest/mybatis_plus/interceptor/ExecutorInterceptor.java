package com.example.hdlitest.mybatis_plus.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-17 14:15
 */
@Intercepts({
        @Signature(
                type= Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )
})
@Slf4j
@Component
public class ExecutorInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("拦截器ExecutorInterceptor");

        // 获取原始sql语句
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String oldsql = boundSql.getSql();
        log.info("old:"+oldsql);

//        String sql = ExecutorPluginUtils.getSqlByInvocation(invocation);
//        //可以对sql重写
//        log.error("拦截器ExecutorInterceptor:"+sql);
//        //sql = "SELECT id from BUS_RECEIVER where id = ? ";
//        ExecutorPluginUtils.resetSql2Invocation( invocation,  sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
