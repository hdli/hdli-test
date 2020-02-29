package com.example.hdlitest.juc.collections.queue.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-5 21:00
 */
public class ArrayBlockingQueueTest {
    /**
     * 用数组实现的有界阻塞队列。此队列按照先进先出（FIFO）的原则对元素进行排序。默认情况下
     * 不保证访问者公平的访问队列
     */
    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) throws InterruptedException {

//        blockingQueue.add("a");
//        blockingQueue.add("b");
//        blockingQueue.add("c");
//        blockingQueue.add("d");
//        blockingQueue.add("e");
//
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始");
            System.out.println(Thread.currentThread().getName()+"队列大小："+blockingQueue.size());
            try {
                System.out.println(Thread.currentThread().getName()+"获取的元素："+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"结束");
        }).start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"开始");
            try {
                Thread.sleep(3000);
                blockingQueue.put("a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"队列大小："+blockingQueue.size());
            System.out.println(Thread.currentThread().getName()+"结束");
        }).start();



    }
}
