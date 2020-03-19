package com.example.hdlitest.base;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 *
 *
 * curator 增删改
 *
 * @author Lihuidong
 * @version 1.0
 * @date 2020/3/13 16:53
 */
public class CuratorDemo {

    public static void main(String[] args) throws Exception {

        /**
         * connectString：zk的server地址，多个server之间使用英文逗号分隔开
         * connectionTimeoutMs：连接超时时间，如上是30s，默认是15s
         * sessionTimeoutMs：会话超时时间，如上是50s，默认是60s
         *
         * 参数RetryPolicy提供重试策略的接口，可以让用户实现自定义的重试策略。
         * 默认提供了以下实现，分别为
         * ExponentialBackoffRetry（baseSleepTimeMs：初始的sleep时间，用于计算之后的每次重试的sleep时间，
         *                          maxRetries：最大重试次数，
         *                          maxSleepMs：最大sleep时间，如果上述的当前sleep计算出来比这个大，那么sleep用这个时间）、
         * BoundedExponentialBackoffRetry、
         * RetryForever、
         * RetryNTimes、
         * RetryOneTime(仅仅只重试一次)、
         * RetryUntilElapsed
         *
         * start()会阻塞到会话创建成功为止
         */
        //有两种方式创建
//        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient();

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.4.56:2181").sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,2))
                .build();

        curatorFramework.start();

        createData(curatorFramework);

    }

    private static void createData(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                forPath("/data/program","test".getBytes());
    }

    private static void updateData(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.setData().forPath("/data/program","up".getBytes());
    }

    private static void deleteData(CuratorFramework curatorFramework) throws Exception {
        //传入一个旧的stat变量,来存储服务端返回的最新的节点状态信息
        Stat stat=new Stat();
        String value=new String(curatorFramework.getData().storingStatIn(stat).forPath("/data/program"));
        curatorFramework.delete().withVersion(stat.getVersion()).forPath("/data/program");
    }



}
