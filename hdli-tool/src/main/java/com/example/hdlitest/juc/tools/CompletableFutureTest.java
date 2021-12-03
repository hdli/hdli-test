package com.example.hdlitest.juc.tools;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author luyi
 * @date 2021/12/3 3:03 下午
 */
public class CompletableFutureTest {

    /**
     *
     * 参考资料：https://www.jianshu.com/p/6bac52527ca4
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("开始");
        long startNanoTime = System.nanoTime();
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(()-> {
            int t = new Random().nextInt(5);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1="+t);
        });

        CompletableFuture<Void> f2 = CompletableFuture.runAsync(()->{
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2="+t);
        });
        CompletableFuture<Void> voidCompletableFuture = f1.runAfterBoth(f2, new Runnable() {
            @Override
            public void run() {
                long escapedMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanoTime);
                System.out.println("上面两个任务都执行完成了。时间："+escapedMillis);
            }
        });
        voidCompletableFuture.join();
        System.out.println("结束");
    }

    public static void test(){
        System.out.println("开始");

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1="+t);
                return t;
            }
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2="+t);
                return t;
            }
        });
        CompletableFuture<Void> voidCompletableFuture = f1.runAfterBoth(f2, new Runnable() {
            @Override
            public void run() {
                System.out.println("上面两个任务都执行完成了。");
            }
        });
        voidCompletableFuture.join();
        System.out.println("结束");
    }

}
