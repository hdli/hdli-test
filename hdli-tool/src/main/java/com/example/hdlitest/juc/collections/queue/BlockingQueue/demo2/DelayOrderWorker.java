package com.example.hdlitest.juc.collections.queue.BlockingQueue.demo2;


/**
 *
 * 具体执行相关业务的业务类
 * @author 李会东
 * @version 1.0
 * @date 2019-8-23 10:51
 */
public class DelayOrderWorker implements Runnable {

    @Override
    public void run() {
        //相关业务逻辑处理
        System.out.println(Thread.currentThread().getName()+" do something ……");
    }
}
