package com.example.hdlitest.juc.lock.test.test1.method2;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-30 18:02
 */
public class PrintOddEven {

    public static void main(String[] args) {
        Number num = new Number();
        OddPrinter oddPrinter = new OddPrinter(num);
        EvenPrinter evenPrinter = new EvenPrinter(num);

        Thread oddThread = new Thread(oddPrinter, "oddThread");
        Thread evenThread = new Thread(evenPrinter, "evenThread");
        oddThread.start();
        evenThread.start();
    }
}
