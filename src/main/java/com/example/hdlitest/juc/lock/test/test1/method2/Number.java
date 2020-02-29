package com.example.hdlitest.juc.lock.test.test1.method2;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-30 18:01
 */
public class Number {
    private int count = 1;

    private Object lock = new Object();

    /**
     * 打印奇数
     */
    public void printOdd() {
        synchronized(lock) {
            try {
                if(count % 2 != 1) {
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + "=======" + count);
                count++;
                lock.notify();
            } catch(InterruptedException e) {

            }
        }
    }

    /**
     * 打印偶数
     */
    public void printEven() {
        synchronized(lock) {
            try {
                if(count % 2 != 0) {
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + "=======" + count);
                count++;
                lock.notify();
            } catch(InterruptedException e) {

            }
        }
    }
}
