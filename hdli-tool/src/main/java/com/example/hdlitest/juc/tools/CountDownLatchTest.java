package com.example.hdlitest.juc.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-5 22:33
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 利用它可以实现类似计数器的功能。比如有
         * 一个任务 A，它要等待其他 4 个任务执行完毕之后才能执行，此时就可以利用 CountDownLatch
         * 来实现这种功能了
         */
        final CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            executorService.execute(new Item(latch));
        }
        executorService.shutdown();
        System.out.println("等待 2 个子线程执行完毕...");
        latch.await();
        System.out.println("2 个子线程已经执行完毕");
        System.out.println("继续执行主线程");
    }

    static class Item implements Runnable{

        private CountDownLatch latch;

        public Item(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
            latch.countDown();
        }
    }
}
