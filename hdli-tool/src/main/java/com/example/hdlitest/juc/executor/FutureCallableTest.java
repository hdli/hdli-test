package com.example.hdlitest.juc.executor;

import java.util.concurrent.*;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-12 16:52
 */
public class FutureCallableTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> task = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName());
            return "bbbb";
        });

        new Thread(task).start();



        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                return "aaaa";
            }
        });

        System.out.println(task.get());
        System.out.println(future.get());


        testCompletableFuture2();

    }


    public static void testCompletableFuture2() throws ExecutionException, InterruptedException {
        //complete(T value)方法用于手动完成CompletableFuture对象的异步任务，并设置其结果。通过调用此方法，
        // 可以将一个特定的结果设置到CompletableFuture对象中，任何等待该异步任务的操作都会得到这个预先设置的结果。
        // 一旦调用了complete()方法，CompletableFuture对象的状态会立即变为已完成，并且之后任何对该对象的计算都不会再触发异步任务的执行‌

        CompletableFuture<String> future = new CompletableFuture<>();

        ExecutorService service = Executors.newSingleThreadExecutor();

        service.execute(() -> {
            try {
                Thread.sleep(3000);
                int i = 10/0;
                System.out.println(Thread.currentThread().getName() +":异步任务开始执行");
                System.out.println(Thread.currentThread().getName() +":异步任务执行结束");
                future.complete("返回结果");
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });
        System.out.println("---------------------------------");
        String result = future.get();

        System.out.println(Thread.currentThread().getName() + ":" +result);

    }



    public static void testCompletableFuture(){

        System.out.println("-------------------------------------");
        // 生产者，可以指定返回结果
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务开始执行");
            System.out.println("异步任务执行结束");
            return "返回结果";
        });
        //CompletableFuture 中的 get() 和 join() 方法都用于获取异步任务的执行结果，但是在使用时需要注意以下几点区别：
        //1. 抛出异常的方式不同：如果异步任务执行过程中出现异常， get() 方法会抛出 ExecutionException 异常，而 join() 方法会抛出 CompletionException 异常，这两个异常都是继承自 RuntimeException 的。
        //2. 方法调用限制不同： join() 方法是不可以被中断的，一旦调用就必须等待任务执行完成才能返回结果；而 get() 方法可以在调用时设置等待的超时时间，如果超时还没有获取到结果，就会抛出 TimeoutException 异常。
        //3. 返回结果类型不同： get() 方法返回的是异步任务的执行结果，该结果是泛型类型 T 的，需要强制转换才能获取真正的结果；而 join() 方法返回的是异步任务的执行结果，该结果是泛型类型 T，不需要强制转换。
        //4. 推荐使用方式不同：推荐在 CompletableFuture 中使用 join() 方法，因为它没有受到 interrupt 的干扰，不需要捕获异常，也不需要强制类型转换。


        String result1 = firstTask.join();
        String result2 = null;
        try {
            result2 = firstTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(result1 + "," + result2);

    }
}
