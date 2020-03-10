package com.example.hdlitest.juc.lock.test.test2.method1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-30 18:25
 */
public class ThreeThreadPrintABC {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition wait = lock.newCondition();
    // 用来控制该打印的线程
    private static int count = 0;

    static class PrintObj implements Runnable {
        private int num;
        private String obj;

        public PrintObj(int num, String obj) {
            this.num = num;
            this.obj = obj;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while ((count % 3) != num) {
                        wait.await();
                    }
                    System.out.println(Thread.currentThread().getName() + ":"+obj);
                    count++;
                    wait.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    public static void main(String[] args) {
        Thread printA = new Thread(new PrintObj(0,"A"));
        Thread printB = new Thread(new PrintObj(1,"B"));
        Thread printC = new Thread(new PrintObj(2,"C"));
        printA.start();
        printB.start();
        printC.start();
    }
}
