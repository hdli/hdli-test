package com.example.hdlitest.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition由ReentrantLock对象创建,并且可以同时创建多个
 * Condition接口在使用前必须先调用ReentrantLock的lock()方法获得锁。
 * 之后调用Condition接口的await()将释放锁,并且在该Condition上等待,直到有其他线程调用Condition的signal()方法唤醒线程。
 * 使用方式和wait,notify类似
 * @author 李会东
 * @version 1.0
 * @date 2019-11-7 17:17
 */
public class ReentrantLockTest {
    /**
     *  //Lock lock=new ReentrantLock(true);//公平锁
     *     //Lock lock=new ReentrantLock(false);//非公平锁
     */
    private static Lock lock = new ReentrantLock();

    /**
     * 创建 Condition
     */
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        lock.lock();
        new Thread(new SignalThread()).start();
        System.out.println("主线程等待通知");
        try {
            condition.await();
        } finally {
            System.out.println("主线程释放锁");
            lock.unlock();
        }
        System.out.println("主线程恢复运行");
    }

    static class SignalThread implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                condition.signal();
                System.out.println("子线程通知");
            }finally {
                System.out.println("子线程释放锁");
                lock.unlock();
            }

        }
    }
}
