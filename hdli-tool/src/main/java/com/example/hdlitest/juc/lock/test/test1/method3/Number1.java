package com.example.hdlitest.juc.lock.test.test1.method3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-30 18:03
 */
public class Number1 {

    private int count = 1;

    private ReentrantLock lock = new ReentrantLock();
    // 为打印奇数的线程注册一个Condition
    public Condition conditionOdd = lock.newCondition();
    // 为打印偶数的线程注册一个Condition
    public Condition conditionEven = lock.newCondition();

    /**
     * 打印奇数
     */
    public void printOdd() {
        try {
            lock.lock();
            if(count % 2 != 1) {
                conditionOdd.await();
            }
            System.out.println(Thread.currentThread().getName() + "=======" + count);
            count++;
            conditionEven.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 打印偶数
     */
    public void printEven() {
        try {
            lock.lock();
            if(count % 2 != 0) {
                conditionEven.await();
            }
            System.out.println(Thread.currentThread().getName() + "=======" + count);
            count++;
            conditionOdd.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
