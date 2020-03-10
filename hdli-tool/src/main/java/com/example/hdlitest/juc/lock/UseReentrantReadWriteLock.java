package com.example.hdlitest.juc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *读写锁：分为读锁和写锁，多个读锁不互斥，读锁与写锁互斥，这是由 jvm 自己控制的，你只要
 * 上好相应的锁即可
 *
 * ReentrantReadWriteLock使得多个读线程同时持有读锁（只要写锁未被占用），而写锁是独占的。
 * 但是，读写锁如果使用不当，很容易产生“饥饿”问题
 * 比如在读线程非常多，写线程很少的情况下，很容易导致写线程“饥饿”
 *
 * 所有：StampedLock类，在JDK1.8时引入，是对读写锁ReentrantReadWriteLock的增强，
 * 该类提供了一些功能，优化了读锁、写锁的访问，同时使读写锁之间可以互相转换，更细粒度控制并发。
 *
 * @author 李会东
 * @version 1.0
 * @date 2019-11-8 17:48
 */
public class UseReentrantReadWriteLock {
    /**
     * 读写锁对象
     */
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    /**
     * 获得读锁
     */
    private ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    /**
     * 获得写锁
     */
    private ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();


    public void read() {
        readLock.lock();
        try {
            System.err.println("线程 " + Thread.currentThread().getName() + " 进入了读方法。。。");
            Thread.sleep(3000);
            System.err.println("线程 " + Thread.currentThread().getName() + " 推出了读方法。。。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        writeLock.lock();
        try {
            System.err.println("线程 " + Thread.currentThread().getName() + " 进入了写方法。。。");
            Thread.sleep(3000);
            System.err.println("线程 " + Thread.currentThread().getName() + " 执行了写方法。。。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        UseReentrantReadWriteLock urwLock = new UseReentrantReadWriteLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                urwLock.read();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                urwLock.read();
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                urwLock.write();
            }
        }, "t3");

        t1.start();

        t2.start();
        t3.start();

    }


}
