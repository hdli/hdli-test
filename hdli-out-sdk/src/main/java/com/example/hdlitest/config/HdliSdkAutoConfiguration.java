package com.example.hdlitest.config;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author luyi
 * @date 2023/2/24 8:15 下午
 */
@Configuration
@ComponentScan(
        basePackages = {"com.example.hdlitest"}
)
@RetrofitScan({"com.example.hdlitest"})
public class HdliSdkAutoConfiguration {
}
