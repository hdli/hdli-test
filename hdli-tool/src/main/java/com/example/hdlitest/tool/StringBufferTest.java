package com.example.hdlitest.tool;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-8 16:45
 */
public class StringBufferTest {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getId()+"主线程");
        new MyThread(()->{
            System.out.println(Thread.currentThread().getId()+"子线程");
        }).start();

    }

    static class MyThread extends Thread{

        public MyThread(Runnable target) {
            super(target);
        }

        @Override
        public void run() {
//            super.run();
            System.out.println(Thread.currentThread().getId()+"自定义的run");
        }
    }

}
