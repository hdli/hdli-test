package com.example.hdlitest.tool.guava.eventBus;

/**
 *
 * 事件类型(类似MQ中的topic)
 * @author luyi
 * @date 2024/5/30 16:32
 */
public class LoginEvent {

    public String message;


    LoginEvent(String message) {
        this.message = message;
    }
}
