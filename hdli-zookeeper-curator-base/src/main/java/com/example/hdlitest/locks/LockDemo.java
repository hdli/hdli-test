package com.example.hdlitest.locks;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author Lihuidong
 * @version 1.0
 * @date 2020/3/15 16:06
 */
public class LockDemo {


    public static void main(String[] args) {

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181").sessionTimeoutMs(50000)
                .retryPolicy(new ExponentialBackoffRetry(1000,2))
                .build();

        curatorFramework.start();

        /**
         * InterProcessMutex：分布式可重入排它锁
         * InterProcessSemaphoreMutex：分布式排它锁
         * InterProcessReadWriteLock：分布式读写锁
         */
        final InterProcessMutex lock=new InterProcessMutex(curatorFramework,"/locks");
        for(int i=0;i<10;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"->尝试竞争锁");
                try {
                    lock.acquire(); //阻塞竞争锁

                    System.out.println(Thread.currentThread().getName()+"->成功获得了锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(40000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        lock.release(); //释放锁
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },"Thread-"+i).start();
        }
    }
}
