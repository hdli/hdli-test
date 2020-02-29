package com.example.hdlitest.redis.redisson.annontation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedissonLock {

    /**
     * 要锁的参数
     * 默认为空 锁整个方法名
     * @return
     */
    String lockKey() default "";

    /**
     * 最多等待时间（单位：秒）
     * @return
     */
    int waitTime() default 5;



}
