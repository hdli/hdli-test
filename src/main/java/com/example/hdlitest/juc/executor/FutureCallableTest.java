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
    }
}
