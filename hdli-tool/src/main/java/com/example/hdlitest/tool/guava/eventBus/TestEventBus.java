package com.example.hdlitest.tool.guava.eventBus;

import com.google.common.eventbus.EventBus;

/**
 * @author luyi
 * @date 2024/5/30 16:31
 */
public class TestEventBus {

    public static void main(String...args) {
        // 定义一个EventBus对象
        EventBus eventBus = new EventBus();
        // 向上述EventBus对象中注册一个监听对象
        eventBus.register(new LoginEventListener());
        // 使用EventBus发布一个事件，该事件会给通知到所有注册的监听器
        eventBus.post(new LoginEvent("Hello every listener, joke begins..."));
    }

}
