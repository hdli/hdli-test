package com.example.hdlitest.juc.collections.queue.BlockingQueue;

import java.util.concurrent.SynchronousQueue;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-5 22:21
 */
public class SynchronousQueueTest {

    private static SynchronousQueue<String> queue = new SynchronousQueue();
    public static void main(String[] args) throws InterruptedException {
        queue.put("a");
        System.out.println("放完数据");
//        new Thread(()->{
//            System.out.println(Thread.currentThread().getName()+"开始");
//            try {
//                queue.put("a");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"结束");
//        }).start();
//        new Thread(()->{
//            System.out.println(Thread.currentThread().getName()+"开始");
//            try {
//                System.out.println(Thread.currentThread().getName()+"获取多列中的值："+queue.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"结束");
//        }).start();

    }
}
