package com.example.hdlitest.juc.lock.test.test1.method1;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-30 17:43
 */
public class TestPrint {

    public static void main(String[] args) {
        Num num = new Num();
        Odd odd = new Odd(num);
        Even even = new Even(num);

        Thread t1 = new Thread(odd, "threadOdd");
        Thread t2 = new Thread(even, "threadEven");
        t1.start();
        t2.start();
    }
}
