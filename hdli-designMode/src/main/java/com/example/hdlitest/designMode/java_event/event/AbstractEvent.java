package com.example.hdlitest.designMode.java_event.event;

import java.util.EventObject;

/**
 * 任务结束事件TaskFinishEvent
 * 自定义事件类型TaskFinishEvent继承自JDK中的EventObject,构造时会传入Task作为事件源
 * @author 李会东
 * @version 1.0
 * @date 2019-12-16 11:24
 */
public abstract class AbstractEvent extends EventObject{
    private static final long serialVersionUID = 7382177883468599220L;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public AbstractEvent(Object source) {
        super(source);
    }
}
