package com.example.hdlitest.tool.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * 限流
 * @author 李会东
 * @version 1.0
 * @date 2019-12-12 22:30
 */
public class AccessLimitService {

    /**
     * 常用的限流算法有漏桶算法和令牌桶算法，
     * guava的RateLimiter使用的是令牌桶算法，也就是以固定的频率向桶中放入令牌，例如一秒钟10枚令牌，
     * 实际业务在每次响应请求之前都从桶中获取令牌，只有取到令牌的请求才会被成功响应，
     * 获取的方式有两种：阻塞等待令牌或者取不到立即返回失败
     *
     */

    /**每秒只发出5个令牌
     * 我们通过RateLimiter.create(5.0)配置的是每一秒5枚令牌，但是限流的时候发出的是6枚，改用其他值验证，也是实际的比配置的大1
     */
    private static RateLimiter rateLimiter = RateLimiter.create(5);

    /**
     * 尝试获取令牌
     * @return
     */
    public static boolean tryAcquire(){
        //acquire() 从RateLimiter获取一个许可，该方法会被阻塞直到获取到请求
        return rateLimiter.tryAcquire();
    }


    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(5);
        while(true){
            rateLimiter.acquire(10);
            System.out.println("获取令牌");
        }

    }
}
