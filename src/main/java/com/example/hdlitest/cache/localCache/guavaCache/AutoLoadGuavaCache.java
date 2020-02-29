package com.example.hdlitest.cache.localCache.guavaCache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-24 16:51
 */
public class AutoLoadGuavaCache {

    /**
     * 自动加载
     * Cache的get方法有两个参数，第一个参数是要从Cache中获取记录的key，
     * 第二个记录是一个Callable对象。
     * 当缓存中已经存在key对应的记录时，get方法直接返回key对应的记录。
     * 如果缓存中不包含key对应的记录，Guava会启动一个线程执行Callable对象中的call方法，
     * call方法的返回值会作为key对应的值被存储到缓存中，并且被get方法返回
     *
     *
     * 从结果中可以看出，虽然是两个线程同时调用get方法，但只有一个get方法中的Callable会被执行。
     * Guava可以保证当有多个线程同时访问Cache中的一个key时，如果key对应的记录不存在，
     * Guava只会启动一个线程执行get方法中Callable参数对应的任务加载数据存到缓存。
     * 当加载完数据后，任何线程中的get方法都会获取到key对应的值
     */
    private static Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(3)
            .build();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("thread1");
            try {
                String value = cache.get("key", new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("load1"); //加载数据线程执行标志
                        Thread.sleep(1000); //模拟加载时间
                        return "auto load by Callable";
                    }
                });
                System.out.println("thread1 " + value);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("thread2");
            try {
                String value = cache.get("key", new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("load2"); //加载数据线程执行标志
                        Thread.sleep(1000); //模拟加载时间
                        return "auto load by Callable";
                    }
                    });
                    System.out.println("thread2 "+value);
                } catch(ExecutionException e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
