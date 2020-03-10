package com.example.hdlitest.juc.lock.test.test1.method2;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-30 18:01
 */
public class OddPrinter implements Runnable {
    private Number num;

    public OddPrinter(Number num) {
        this.num = num;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            num.printOdd();
        }
    }
}
