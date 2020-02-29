package com.example.hdlitest.designMode.spring_event.publisher;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 *
 * 该接口主要用于发布一些事件时使用，
 * @author 李会东
 * @version 1.0
 * @date 2019-12-26 18:25
 */
@Component
public class EventPublisher2 implements ApplicationEventPublisherAware {
    private static ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

        EventPublisher2.applicationEventPublisher = applicationEventPublisher;
    }

    public static void publishEvent(ApplicationEvent event){
        applicationEventPublisher.publishEvent(event);
    }
}
