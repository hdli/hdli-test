package com.example.hdlitest.mybatis_plus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-17 11:26
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.example.hdlitest.mybatis_plus.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件  自动识别数据库类型 多租户
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // paginationInterceptor.setLimit(你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制);
        return new PaginationInterceptor();
    }
}
