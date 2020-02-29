package com.example.hdlitest.controller;

import com.example.hdlitest.spring_aop.annontation.RetryOnFailure;
import com.example.hdlitest.redis.redisson.annontation.RedissonLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-11 11:11
 */
@Service
public class TestService {
    private Logger logger = LoggerFactory.getLogger(TestService.class);
    @Autowired
    private TestService2 service2;



    private AtomicInteger i = new AtomicInteger(0);

    @RetryOnFailure(retryTimes = 8)
    public String retry(){
        int o = i.incrementAndGet();
        System.out.println("第"+o+"次进入");
        if (o == 5){
            return "aaaaa";
        }
//        throw new RuntimeException("异常");
        return null;
    }

    @RedissonLock(lockKey = "redissonLock")
    public String redissonLock() throws InterruptedException {
        logger.info(Thread.currentThread().getName() +"进入redissonLock");

        return service2.redissonLock2();
    }

}
