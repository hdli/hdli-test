package com.example.hdlitest.base;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置节点的权限
 *
 * @author Lihuidong
 * @version 1.0
 * @date 2020/3/13 17:54
 */
public class AclDemo {


    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.4.56:2181").sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,2))
                .build();

        curatorFramework.start();

        List<ACL> list=new ArrayList<>();
        ACL acl=new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE,
                new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin")));
        list.add(acl);


//        curatorFramework.create().withMode(CreateMode.PERSISTENT).withACL(list).forPath("/auth");

        curatorFramework.setACL().withACL(ZooDefs.Ids.CREATOR_ALL_ACL).forPath("/temp");

        curatorFramework.close();
    }


}
