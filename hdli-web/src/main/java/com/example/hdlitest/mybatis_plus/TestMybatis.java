package com.example.hdlitest.mybatis_plus;

import com.example.hdlitest.mybatis_plus.mapper.AdminMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-17 21:42
 */
public class TestMybatis {
    /**
     * 根据MyBatis 的配置规范配置好后，通过SqlSession.getMapper(XXXMapper.class) 方法，
     * MyBatis 会根据相应的接口声明的方法信息，通过动态代理机制生成一个Mapper 实例，
     * 我们使用Mapper 接口的某一个方法时，MyBatis 会根据这个方法的方法名和参数类型，确定Statement Id，
     * 底层还是通过SqlSession.select("statementId",parameterObject);或者SqlSession.update("statementId",parameterObject);
     * 等等来实现对数据库的操作
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        InputStream resource = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession session = factory.openSession();
        AdminMapper mapper = session.getMapper(AdminMapper.class);

    }
}
