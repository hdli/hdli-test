package com.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-20 11:14
 */
@Configuration
@ConditionalOnClass(Hello.class)                      //   当给定的类名在类路径上存在，则实例化当前Bean
@EnableConfigurationProperties(HdliProperties.class)  //   使HdliProperties生效
public class HdliAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean                         //   当给定的在bean不存在时,则实例化当前Bean
    public Hello hello(){
        return new HelloImpl();
    }
}
