package com.example.hdlitest.juc.lock.test.test2.method3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  基于一个ReentrantLock和三个conditon实现连续打印abcabc...
 * @author 李会东
 * @version 1.0
 * @date 2019-12-30 18:32
 */
public class PrintABCTest implements Runnable {
    // 打印次数
    private static final int PRINT_COUNT = 10;
    // 打印锁
    private final ReentrantLock lock;
    // 本线程打印所需的condition
    private final Condition thisCondition;
    // 下一个线程打印所需的condition
    private final Condition nextCondition;
    // 打印字符
    private final char printChar;

    public PrintABCTest(ReentrantLock lock, Condition thisCondition, Condition nextCondition, char printChar) {
        this.lock = lock;
        this.thisCondition = thisCondition;
        this.nextCondition = nextCondition;
        this.printChar = printChar;
    }

    @Override
    public void run() {
        // 获取打印锁 进入临界区
        lock.lock();
        try {
            // 连续打印PRINT_COUNT次
            for (int i = 0; i < PRINT_COUNT; i++) {
                //打印字符
                System.out.println(printChar);
                // 使用nextCondition唤醒下一个线程
                // 因为只有一个线程在等待，所以signal或者signalAll都可以
                nextCondition.signal();
                // 不是最后一次则通过thisCondtion等待被唤醒
                // 必须要加判断，不然虽然能够打印10次，但10次后就会直接死锁
                if (i < PRINT_COUNT -1) {
                    try {
                        // 本线程让出锁并等待唤醒
                        thisCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            // 释放打印锁
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 写锁
        ReentrantLock lock = new ReentrantLock();
        // 打印a线程的condition
        Condition conditionA = lock.newCondition();
        // 打印b线程的condition
        Condition conditionB = lock.newCondition();
        // 打印c线程的condition
        Condition conditionC = lock.newCondition();
        // 实例化A线程
        Thread printerA = new Thread(new PrintABCTest(lock, conditionA, conditionB, 'A'));
        // 实例化B线程
        Thread printerB = new Thread(new PrintABCTest(lock, conditionB, conditionC, 'B'));
        // 实例化C线程
        Thread printerC = new Thread(new PrintABCTest(lock, conditionC, conditionA, 'C'));
        // 依次开始A B C线程
        printerA.start();
        Thread.sleep(100);
        printerB.start();
        Thread.sleep(100);
        printerC.start();
    }
}
