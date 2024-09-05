package com.example.hdlitest.tool.timewheel;

/**
 * @author luyi
 * @date 2024/9/4 21:21
 */
public interface TimerFuture {

    TimerTask getTask();

    boolean cancel();

    boolean isCancelled();

    boolean isDone();
}
