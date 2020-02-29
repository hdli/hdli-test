package com.example.hdlitest.spring_aop;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-13 16:01
 */
@Configuration
public class InterceptorConfig {

    public static final String TRACE_EXECUTION = "execution(* com.example.hdlitest.spring_aop..*.*(..))";

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor2() {
        TracingInterceptor interceptor = new TracingInterceptor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(TRACE_EXECUTION);
        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}
