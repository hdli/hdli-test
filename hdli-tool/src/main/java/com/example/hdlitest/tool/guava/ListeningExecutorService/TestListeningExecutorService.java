package com.example.hdlitest.tool.guava.ListeningExecutorService;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luyi
 * @date 2024/5/30 11:21
 */
public class TestListeningExecutorService {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        runCallable(listeningExecutorService);

    }




    public static void runCallable(ListeningExecutorService listeningExecutorService){
        ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("执行业务逻辑");
                Thread.sleep(3000);
                return 200;
            }
        });
        //注册回调函数，监听任务完成时间 ******不会阻塞主线程******
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {

            @Override
            public void onSuccess(@Nullable Integer integer) {
                System.out.println("任务成功时调用");
                System.out.println(integer);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("任务失败时调用");
                System.out.println(throwable.getMessage());
            }
        }, listeningExecutorService);

        System.out.println("继续执行主线任务");


    }
}
