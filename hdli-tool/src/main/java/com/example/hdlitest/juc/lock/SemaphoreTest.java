package com.example.hdlitest.juc.lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-11-8 15:10
 */
public class SemaphoreTest {
    /**
     *  同步关键类
     * 构造方法传入的数字是多少，则同一个时刻，只运行多少个进程同时运行制定代码
     */
    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyThread());
        }
        executorService.shutdown();

    }

    static class MyThread implements Runnable{
        private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        @Override
        public void run() {
            try {
                /**
                 * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
                 * 因为semaphore的构造方法是1，则同一时刻只允许一个线程进入，其他线程只能等待。
                 * */
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ":doSomething start-" + getFormatTimeStr());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + ":doSomething end-" + getFormatTimeStr());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }

        }

        public static String getFormatTimeStr() {
            return sf.format(new Date());
        }
    }
}
