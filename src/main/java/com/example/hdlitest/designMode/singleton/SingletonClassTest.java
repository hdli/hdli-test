package com.example.hdlitest.designMode.singleton;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 *
 * @author 李会东
 * @version 1.0
 * @date 2019-12-7 22:30
 */
public class SingletonClassTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future future = new FutureTask(new Callable<SingletonClassTest>() {
            @Override
            public SingletonClassTest call() throws Exception {
                return EnumSingleton.INSTANCE.getInstance();
            }
        });
        Future future2 = new FutureTask(new Callable<SingletonClassTest>() {
            @Override
            public SingletonClassTest call() throws Exception {
                return EnumSingleton.INSTANCE.getInstance();
            }
        });

        new Thread((Runnable) future).start();
        new Thread((Runnable) future2).start();

        Thread.sleep(2000);

        SingletonClassTest a = null;
        if (future.isDone()){
            a = (SingletonClassTest) future.get();
            System.out.println(a.hashCode());
        }
        SingletonClassTest b = null;
        if (future.isDone()){
            b = (SingletonClassTest) future2.get();
            System.out.println(b.hashCode());
        }
        System.out.println(a == b);

    }

}
