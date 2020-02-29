package com.example.hdlitest.juc.lock.test.test1.method1;

/**
 * 打印奇数的线程
 * @author 李会东
 * @version 1.0
 * @date 2019-12-30 17:42
 */
public class Odd implements Runnable {
    private Num num;

    public Odd(Num num) {
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            num.printOdd();
        }
    }
}
