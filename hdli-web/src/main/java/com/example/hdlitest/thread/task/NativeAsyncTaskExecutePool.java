package com.example.hdlitest.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 配置默认的线程池
 * @author 李会东
 * @version 1.0
 * @date 2019-6-26 18:05
 */
@EnableConfigurationProperties({TaskThreadPoolConfig.class})
@EnableAsync
@Configuration
public class NativeAsyncTaskExecutePool implements AsyncConfigurer {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     *  注入配置类
     */
    @Autowired
    private TaskThreadPoolConfig config;

    /**
     * 重写spring默认线程池的方式使用的时候，只需要加@Async注解就可以，不用去声明线程池类。
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();

        //核心线程池大小
        executor.setCorePoolSize(config.getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(config.getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(config.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        //线程名字前缀
        executor.setThreadNamePrefix("MyExecutor-");

        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     *  异步任务中异常处理
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (Throwable arg0, Method arg1, Object... arg2) -> {
            log.error("==========================" + arg0.getMessage() + "=======================", arg0);
            log.error("exception method:" + arg1.getName());
        };
    }


    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new Thread(()-> {
//            System.out.println(Thread.currentThread().getName()+"开始执行");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"执行结束");
//        });
//        thread1.start();
//        thread1.join();
//        System.out.println(Thread.currentThread().getName()+"继续执行");

        Object a = new Object();
        System.out.println("对象初始化");
        synchronized (a){
            a.wait();
        }

        System.out.println("对象wait");
        synchronized (a){
            a.notify();
        }
        System.out.println("结束");
    }

}
