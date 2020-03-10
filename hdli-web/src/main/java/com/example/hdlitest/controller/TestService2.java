package com.example.hdlitest.controller;

import com.example.hdlitest.redis.redisson.annontation.RedissonLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-12 15:42
 */
@Service
public class TestService2 {
    private Logger logger = LoggerFactory.getLogger(TestService2.class);

    @Autowired
    private TestService service;
    /**
     * 测试Redisson 是否可重入
     * @return
     */
    @RedissonLock(lockKey = "redissonLock")
    public String redissonLock2() throws InterruptedException {

        logger.info(Thread.currentThread().getName()+"开始执行");

        Thread.sleep(40000);

        logger.info(Thread.currentThread().getName()+"结束执行");

        return "bbbbb";
    }
}
