package com.example.hdlitest.cache.localCache.guavaCache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.util.concurrent.TimeUnit;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-24 16:29
 */
public class StudyGuavaCache {
    /**
     * 可以通过CacheBuilder类构建一个缓存对象，CacheBuilder类采用builder设计模式，它的每个方法都返回CacheBuilder本身，直到build方法被调用
     */
    private static Cache<String, String> cache = CacheBuilder.newBuilder().build();

    /**
     * 设置最大存储
     * Guava Cache可以在构建缓存对象时指定缓存所能够存储的最大记录数量。
     * 当Cache中的记录数量达到最大值后再调用put方法向其中添加对象，Guava会先从当前缓存的对象记录中选择一条删除掉，
     * 腾出空间后再将新的对象存储到Cache中
     */
    private static Cache<String, String> cacheMax = CacheBuilder.newBuilder().maximumSize(2).build();

    /**
     * 设置过期时间
     * 在构建Cache对象时，可以通过CacheBuilder类的expireAfterAccess和expireAfterWrite两个方法为缓存中的对象指定过期时间，
     * 过期的对象将会被缓存自动删除。其中，
     * expireAfterWrite方法指定对象被写入到缓存后多久过期，
     * expireAfterAccess指定对象多久没有被访问后过期。
     * 也可以同时用expireAfterAccess和expireAfterWrite方法指定过期时间，这时只要对象满足两者中的一个条件就会被自动过期删除
     */
    private static Cache<String, String> cacheExpire = CacheBuilder.newBuilder().maximumSize(2).expireAfterWrite(3, TimeUnit.SECONDS)
            .build();

    /**
     * 弱引用
     * 可以通过weakKeys和weakValues方法指定Cache只保存对缓存记录key和value的弱引用。
     * 这样当没有其他强引用指向key和value时，key和value对象就会被垃圾回收器回收。
     */
    private static Cache<String, Object> cacheWeak = CacheBuilder.newBuilder().maximumSize(2)
            .weakValues()
            .build();


    public static void main(String[] args) throws InterruptedException {

        //可以看到第三条对象记录的插入，导致了第一条对象记录被删除
        cacheMax.put("key1", "value1");
        cacheMax.put("key2", "value2");
        cacheMax.put("key3", "value3");
        System.out.println("第一个值：" + cacheMax.getIfPresent("key1"));
        System.out.println("第二个值：" + cacheMax.getIfPresent("key2"));
        System.out.println("第三个值：" + cacheMax.getIfPresent("key3"));

        //显示清除
        cacheMax.invalidate("key3");


        /**
         * 设置过期时间
         */
//        cacheExpire.put("key1", "value1");
//        int time = 1;
//        while (true) {
//            System.out.println("第" + time++ + "次取到key1的值为：" + cacheExpire.getIfPresent("key1"));
//            Thread.sleep(1000);
//            if (time > 5) {
//                break;
//            }
//        }

        /**
         * 构建Cache时通过weakValues方法指定Cache只保存记录值的一个弱引用。
         * 当给value引用赋值一个新的对象之后，就不再有任何一个强引用指向原对象。
         * System.gc()触发垃圾回收后，原对象就被清除了
         */
        Object value = new Object();
        cacheWeak.put("key1", value);
        //原对象不再有强引用
        value = new Object();
        System.gc();
        System.out.println(cacheWeak.getIfPresent("key1"));

    }

    /**
     * 移除监听器
     * 可以为Cache对象添加一个移除监听器，这样当有记录被删除时可以感知到这个事件
     */
    public static void listener(){
        RemovalListener<String, String> listener = new RemovalListener<String, String>() {
            @Override
            public void onRemoval(RemovalNotification<String, String> notification) {
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
            }
        };

        /**
         * 移除监听器
         * 可以为Cache对象添加一个移除监听器，这样当有记录被删除时可以感知到这个事件
         */
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build();

        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value3");
        cache.put("key5", "value3");
        cache.put("key6", "value3");
        cache.put("key7", "value3");
        cache.put("key8", "value3");
    }


}
