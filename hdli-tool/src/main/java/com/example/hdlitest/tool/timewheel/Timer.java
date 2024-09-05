package com.example.hdlitest.tool.timewheel;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 定时器
 * @author luyi
 * @date 2024/9/4 21:24
 */
public interface Timer {

    /**
     * 调度定时任务
     */
    TimerFuture schedule(TimerTask task, long delay, TimeUnit unit);

    /**
     * 停止所有调度任务
     */
    Set<TimerTask> stop();
}
