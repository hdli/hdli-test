package com.example.hdlitest.thread.async;

import java.io.IOException;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-7-18 20:03
 */
public class TestJoin {

    /**
     * 当前main方法执行结果发生了死锁.(两个线程互相等待对方执行,因此都不会像下执行)
     * 分析死锁发生的原因：
     *
     *  首先是mian线程开始对这个类进行初始化,对静态的变量分配内存空间,这个时候的值为null,
     *  然后就会执行那个静态初始化块.在静态初始化块里面创建一个线程,并且启动了这个新线程,
     *  并且调用了join的方法.这就意味着mian线程必须等待新线程执行完毕后才可以向下执行的.(静态变量属于类)，
     *  但是这个类正在由main线程初始化,因此新的线程就会暂停等待main线程对这个类执行初始化结束的.不出意外的出现了死锁
     *
     *
     * 我们知道,main线程开始执行对类的初始化的时候,初始化的主要的步骤就是两个
     *
     *        (1):为这个类的所有的静态的变量分配内存空间.
     *
     *        (2):调用静态初始化块代码执行初始化.
     *
     * 注意这两者之间的顺序.
     * 当某一个线程访问一个静态变量的时候,这个类的状态会分大概四种情况的:
     *  (1):该类尚未被初始化,当前线程开始对其进行初始化.
     *
     *  (2):该类正在执行初始化,当前线程就会 递归的执行初始化.
     *
     *  (3):这个类正在被其他的线程执行初始化,当前的线程就会暂停,等待其他的线程初始化完成.
     *
     *  (4):这个类已经初始化完成啦,就会直接得到这个静态变量的值
     *
     *
     *
     */

    private static boolean flag = false;

    static {
        Thread t =new Thread(()->{
            System.out.println("static: "+flag);
            flag = true;
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("static end");
    }

    public static void main(String[] args) throws IOException,InterruptedException {
        System.out.println(flag);
    }
}
