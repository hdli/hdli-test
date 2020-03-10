package com.example.hdlitest.designMode.java_event.listner;

import com.example.hdlitest.designMode.java_event.event.AbstractEvent;

import java.util.EventListener;

/**
 * 继承标记接口EventListner表示该接口的实现类是一个监听器,同时在内部定义了事件发生时的响应方法onTaskFinish(event),接收一个TaskFinishEvent作为参数
 */
public interface TaskFinishEventListner<E extends AbstractEvent> extends EventListener {

    void onTaskFinish(E event);
}
