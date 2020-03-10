package com.example.hdlitest.juc.lock.test.test2.method2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-30 18:30
 */
public class ThreeThreadPrintABC1 {
    private static ReentrantLock lock = new ReentrantLock();

    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    // 用来控制该打印的线程
    private static int count = 0;

    static class PrintA implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    if (count % 3 != 0) {
                        conditionA.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "------A");
                    count++;
                    conditionB.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    static class PrintB implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    if (count % 3 != 1) {
                        conditionB.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "------B");
                    count++;
                    conditionC.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    static class PrintC implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    if (count % 3 != 2) {
                        conditionC.await();
                    }
                    System.out.println(Thread.currentThread().getName() + "------C");
                    count++;
                    conditionA.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread printA = new Thread(new PrintA());
        Thread printB = new Thread(new PrintB());
        Thread printC = new Thread(new PrintC());
        printA.start();
        printB.start();
        printC.start();
    }
}
