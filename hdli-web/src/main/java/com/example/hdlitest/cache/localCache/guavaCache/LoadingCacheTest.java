package com.example.hdlitest.cache.localCache.guavaCache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-24 16:59
 */
public class LoadingCacheTest {

    /**
     * 与构建Cache类型的对象类似，LoadingCache类型的对象也是通过CacheBuilder进行构建，
     * 不同的是，在调用CacheBuilder的build方法时，必须传递一个CacheLoader类型的参数，CacheLoader的load方法需要我们提供实现。
     * 当调用LoadingCache的get方法时，如果缓存不存在对应key的记录，则CacheLoader中的load方法会被自动调用从外存加载数据，
     * load方法的返回值会作为key对应的value存储到LoadingCache中，并从get方法返回
     *
     * 从LoadingCache查询的正规方式是使用get(K)方法。这个方法要么返回已经缓存的值，
     * 要么使用CacheLoader向缓存原子地加载新值（通过load(String key) 方法加载）。
     * 由于CacheLoader可能抛出异常，LoadingCache.get(K)也声明抛出ExecutionException异常。
     * 如果你定义的CacheLoader没有声明任何检查型异常，则可以通过getUnchecked(K)查找缓存；
     * 但必须注意，一旦CacheLoader声明了检查型异常，就不可以调用getUnchecked(K)。
     */
    public static void main(String[] args) throws ExecutionException {
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                //休眠1s，模拟加载数据
                Thread.sleep(1000);
                System.out.println(key + " is loaded from a cacheLoader!");
                return key + "'s value";
            }
        };
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(3)
                //在构建时指定自动加载器
                .build(cacheLoader);
        loadingCache.get("key1");
        loadingCache.get("key2");
        loadingCache.get("key3");
    }
}
