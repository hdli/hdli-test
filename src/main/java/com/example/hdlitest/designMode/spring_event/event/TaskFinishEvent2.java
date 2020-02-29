package com.example.hdlitest.designMode.spring_event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定任务结束事件
 * @author 李会东
 * @version 1.0
 * @date 2019-12-16 14:21
 */
public class TaskFinishEvent2 extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public TaskFinishEvent2(Object source) {
        super(source);
    }

}
