package com.example.hdlitest.mybatis_plus.interceptor;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-17 14:45
 */
public class MybatisInterceptDoc {

    /**
     * https://blog.csdn.net/cowbin2012/article/details/85256360
     *
     * MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括
     * 1,拦截执行器的方法 Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
     * 2,拦截参数的处理 ParameterHandler (getParameterObject, setParameters)
     * 3,拦截结果集的处理,为sql执行之后的结果拦截过滤 ResultSetHandler (handleResultSets, handleOutputParameters)
     * 4,拦截Sql语法构建的处理,为sql执行之前的拦截进行sql封装 StatementHandler (prepare, parameterize, batch, update, query)
     *
     */
}
