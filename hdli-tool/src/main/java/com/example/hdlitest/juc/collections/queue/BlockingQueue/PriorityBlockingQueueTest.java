package com.example.hdlitest.juc.collections.queue.BlockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-5 22:02
 */
public class PriorityBlockingQueueTest {
    /**
     * 是一个支持优先级的无界队列。默认情况下元素采取自然顺序升序排列。可以自定义实现
     * compareTo()方法来指定元素进行排序规则
     */
    private static PriorityBlockingQueue<String> queue = new PriorityBlockingQueue();
    public static void main(String[] args) {
        queue.offer("a");
        queue.offer("d");
        queue.offer("e");
        queue.offer("c");
        queue.offer("b");
        System.out.println(queue.offer("f"));
        System.out.println(queue.size());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }
}
