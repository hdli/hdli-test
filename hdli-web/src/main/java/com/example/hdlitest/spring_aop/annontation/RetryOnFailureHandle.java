package com.example.hdlitest.spring_aop.annontation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * 异常重试 注解 （可在注解中定义重试次数 或在 配置中获取重试次数）
 * https://blog.csdn.net/tianyaleixiaowu/article/details/90036180
 * @author 李会东
 * @version 1.0
 * @date 2019-12-10 22:50
 */
@Aspect
@Component
@Order(1)
public class RetryOnFailureHandle {

    public static final int MAX_RETRY_TIMES = 10;//max retry times
    private Logger log = LoggerFactory.getLogger(getClass());

    @Around("@annotation(retryOnFailure)")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp, RetryOnFailure retryOnFailure) throws Throwable {

        int aa = retryOnFailure.retryTimes();
        System.out.println("注解配置重试次数："+aa);
        int attempts = 0;
        Object result = null;
        do {
            attempts++;
            try {
                result = pjp.proceed();
                return result;
            } catch (Exception e) {
                //TODO
//                if (e instanceof ObjectOptimisticLockingFailureException ||
//                        e instanceof StaleObjectStateException) {
//                    log.info("retrying....times:{}", attempts);
//                    if (attempts > MAX_RETRY_TIMES) {
//                        log.info("retry excceed the max times..");
//                        throw e;
//                    }
//                }
                log.error(e.getMessage());
            }
        } while (attempts < MAX_RETRY_TIMES);

        return result;
    }
}
