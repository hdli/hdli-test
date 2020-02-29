package com.example.hdlitest.juc.collections.queue.BlockingQueue.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 具体执行相关业务的业务类
 * @author 李会东
 * @version 1.0
 * @date 2019-8-23 10:51
 */
public class DelayOrderWorker implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(DelayOrderWorker.class);

    @Override
    public void run() {
        //相关业务逻辑处理
        logger.info(Thread.currentThread().getName()+" do something ……");
    }
}
