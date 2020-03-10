package com.example.hdlitest.redis.redisson.annontation;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-10 23:10
 */

import com.example.hdlitest.redis.redisson.RedissLockUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis 分布式锁 注解切面处理
 */
@Aspect
@Component
@Order(2) //该order必须设置，很关键
public class RedissonLockAspect {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Around("@annotation(redissonLock)")
    public Object around(ProceedingJoinPoint joinPoint, RedissonLock redissonLock) throws Throwable {
        log.info(Thread.currentThread().getName()+"进入RedissonLockAspect 方法");
        Object obj = null;

        String lockKey = redissonLock.lockKey();

        if (StringUtils.isEmpty(lockKey)){
            //取得方法名
            lockKey = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint
                    .getSignature().getName();
        }
        //最多等待多久获取锁
        int waitTime = redissonLock.waitTime();
        boolean res = false;
        try {
            res = RedissLockUtil.tryLock(lockKey,TimeUnit.SECONDS,waitTime);
            if (res){
                log.info(Thread.currentThread().getName()+"取到锁");
                obj = joinPoint.proceed();
            }else {
                log.info("----------nono----------");
                return "未获取到锁";
            }
        }catch (Exception e){
            log.error(Thread.currentThread().getName()+e.getMessage());
            //TODO 异常处理
        }finally {
            if (res){
                RedissLockUtil.unlock(lockKey);
                log.info(Thread.currentThread().getName()+"释放锁");
            }
        }
        return obj;
    }
}
