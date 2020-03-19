package com.example.hdlitest.base;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;

/**
 * @author Lihuidong
 * @version 1.0
 * @date 2020/3/13 18:01
 */
public class WatcherDemo {

    public static void main(String[] args) throws Exception {

        //PathChildCache  --针对于子节点的创建、删除和更新 触发事件
        //NodeCache  针对当前节点的变化触发事件
        //TreeCache  综合事件

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.4.56:2181").sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,2))
                .build();

        curatorFramework.start();

        addListenerWithChild(curatorFramework);


        //避免程序结束
        System.in.read();

    }

    //配置中心
    //创建、修改、删除
    private static void addListenerWithNode(CuratorFramework curatorFramework) throws Exception {
        NodeCache nodeCache=new NodeCache(curatorFramework,"/watch",false);
        NodeCacheListener nodeCacheListener= () -> {
            System.out.println("receive Node Changed");
            System.out.println(nodeCache.getCurrentData().getPath()+"---"+new String(nodeCache.getCurrentData().getData()));
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }

    //实现服务注册中心的时候，可以针对服务做动态感知
    private static void addListenerWithChild(CuratorFramework curatorFramework) throws Exception {
        PathChildrenCache nodeCache=new PathChildrenCache(curatorFramework,"/watch",true);
        PathChildrenCacheListener nodeCacheListener= (curatorFramework1, pathChildrenCacheEvent) -> {
            System.out.println(pathChildrenCacheEvent.getType()+"->"+new String(pathChildrenCacheEvent.getData().getData()));
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start(PathChildrenCache.StartMode.NORMAL);
    }
}
