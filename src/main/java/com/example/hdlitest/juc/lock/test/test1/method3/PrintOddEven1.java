package com.example.hdlitest.juc.lock.test.test1.method3;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-30 18:04
 */
public class PrintOddEven1 {

    public static void main(String[] args) {

        final Number1 num = new Number1();

        /**
         * 打印奇数的线程
         */
        Thread oddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    num.printOdd();
                }
            }
        }, "oddThread");

        /**
         * 打印偶数的线程
         */
        Thread evenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    num.printEven();
                }
            }
        }, "evenThread");
        oddThread.start();
        evenThread.start();
    }
}
