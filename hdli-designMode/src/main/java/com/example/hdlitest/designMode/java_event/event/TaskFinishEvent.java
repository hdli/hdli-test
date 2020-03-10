package com.example.hdlitest.designMode.java_event.event;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-10 16:01
 */
public class TaskFinishEvent extends AbstractEvent {
    private static final long serialVersionUID = -4224684422575944030L;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TaskFinishEvent(Object source) {
        super(source);
    }
}
