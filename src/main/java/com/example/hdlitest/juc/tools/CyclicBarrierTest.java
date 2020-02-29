package com.example.hdlitest.juc.tools;

import java.util.concurrent.*;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-5 22:41
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        /**
         * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环
         * 是因为当所有等待线程都被释放以后，CyclicBarrier 可以被重用
         * 我们暂且把这个状态就叫做 barrier，当调用 await()方法之后，线程就处于 barrier 了
         *
         * 1.public int await()：用来挂起当前线程，直至所有线程都到达 barrier 状态再同时执行后续任务；
         * 2. public int await(long timeout, TimeUnit unit)：让这些线程等待至一定的时间，如果还有
         * 线程没有到达 barrier 状态就直接让到达 barrier 的线程执行后续任务
         */

        //当所有子任务都执行完毕时,barrierAction的run方法会被调用
        CyclicBarrier barrier = new CyclicBarrier(2,()->{
            System.out.println("执行barrierAction操作!");
        });
        for (int i = 0; i < 2; i++) {
            executorService.execute(new Item(barrier));
        }
        executorService.shutdown();
        System.out.println("主线程结束");

    }

    static class Item implements Runnable{

        private CyclicBarrier cyclicBarrier;

        public Item(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
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
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务，比如数据操作");
        }
    }

}
