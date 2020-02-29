package com.example.hdlitest.thread.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-12 14:32
 */
public class InheritableThreadLocalUtils {

    private static final ThreadLocal<Integer> local = new InheritableThreadLocal<>();

    public static void set(Integer t) {
        local.set(t);
    }

    public static Integer get() {
        return local.get();
    }

    public static void remove() {
        local.remove();
    }


    public static void main(String[] args) {
        /**
         * InheritableThreadLocal 在父线程中set的变量 在子线程中可以get得到
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            executorService.execute(new TaskThread(i));
        }
    }

    static class TaskThread implements Runnable{

        Integer taskId;

        public TaskThread(Integer taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            InheritableThreadLocalUtils.set(taskId);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(InheritableThreadLocalUtils.get());
                }
            });
        }
    }

}
