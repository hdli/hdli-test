package com.example.hdlitest.juc.collections.queue.BlockingQueue.demo2;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *
 * 延时队列中的消息体将任务封装为消息体
 * @author 李会东
 * @version 1.0
 * @date 2019-8-23 10:53
 */
public class DelayOrderTask<T extends Runnable> implements Delayed {
    private final long time;
    // 任务类，也就是之前定义的任务类
    private final T task;

    /**
     * @param timeout 超时时间(纳秒)
     * @param task    任务
     */
    public DelayOrderTask(long timeout, T task) {
        this.time = System.nanoTime() + timeout;
        this.task = task;
    }


    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayOrderTask other = (DelayOrderTask) o;
        return Long.compare(this.time,other.time);
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }

    public T getTask() {
        return task;
    }
}
