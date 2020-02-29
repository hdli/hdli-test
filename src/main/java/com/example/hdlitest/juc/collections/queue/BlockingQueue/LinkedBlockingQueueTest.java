package com.example.hdlitest.juc.collections.queue.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-5 21:40
 */
public class LinkedBlockingQueueTest {
    /**
     * 基于链表的阻塞队列
     * 其对于生产者
     * 端和消费者端分别采用了独立的锁来控制数据同步，这也意味着在高并发的情况下生产者和消费
     * 者可以并行地操作队列中的数据，以此来提高整个队列的并发性能
     */
    private static LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue(5);

    public static void main(String[] args) {
        linkedBlockingQueue.add("a");
        linkedBlockingQueue.add("b");
        linkedBlockingQueue.add("c");
        linkedBlockingQueue.add("d");
        linkedBlockingQueue.add("e");

        System.out.println(linkedBlockingQueue.poll());
    }
}
