package com.example.hdlitest.tool.guava.eventBus;

import com.google.common.eventbus.Subscribe;

/**
 *  事件监听器
 * @author luyi
 * @date 2024/5/30 16:31
 */
public class LoginEventListener {


    // 监听的方法，必须使用注解声明，且只能有一个参数，实际触发一个事件的时候会根据参数类型触发方法
    @Subscribe
    public void listen(LoginEvent event) {
        System.out.println("Event listener 1 event.message = " + event.message);
    }
}
