package com.example.hdlitest.juc.lock.common;

import java.util.concurrent.locks.LockSupport;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-12 11:43
 */
public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 而使用LockSupport的话，我们可以在任何场合使线程阻塞，同时也可以指定要唤醒的线程，相当的方便
         *
         * park和unpark的先后顺序并不是那么严格(可以先唤醒线程再阻塞线程)
         * 1.unpark调用时，如果当前线程还未进入park，则许可为true
         * 2.park调用时，判断许可是否为true，如果是true，则继续往下执行；如果是false，则等待，直到许可为true
         *
         *
         */
        Thread parkThread = new Thread(new ParkThread());
        parkThread.start();
        System.out.println(Thread.currentThread().getName()+"开始线程唤醒");
        LockSupport.unpark(parkThread);
        System.out.println(Thread.currentThread().getName()+"结束线程唤醒");
    }

    static class ParkThread implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"开始线程阻塞");
            //LockSupport.park(Object blocker),指定线程阻塞的对象blocker，该对象主要用来排查问题
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"结束线程阻塞");
        }
    }

}
