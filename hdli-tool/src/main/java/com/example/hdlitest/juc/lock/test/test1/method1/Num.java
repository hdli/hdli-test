package com.example.hdlitest.juc.lock.test.test1.method1;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-30 17:39
 */
public class Num {
    private int count = 1;
    /**
     * 打印奇数
     */
    public synchronized void printOdd() {
        try {
            if (count % 2 != 1) {
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + "----------" + count);
            count++;
            this.notify();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 打印偶数
     */
    public synchronized void printEven() {
        try {
            if (count % 2 != 0) {
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + "----------" + count);
            count++;
            this.notify();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
