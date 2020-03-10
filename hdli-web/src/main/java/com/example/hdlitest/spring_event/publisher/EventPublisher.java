package com.example.hdlitest.spring_event.publisher;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 *
 * Spring事件监听机制的类结构分析可知,发布事件的功能定义在ApplicationEventPublisher接口中,
 * 而ApplicationContext继承了该接口,所以最好的方法是通过实现ApplicationContextAware接口获取ApplicationContext实例,
 * 然后调用其发布事件方法
 * @author 李会东
 * @version 1.0
 * @date 2019-12-16 14:26
 */
@Component
public class EventPublisher implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     *  发布事件
     */
    public static void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        EventPublisher.applicationContext = applicationContext;
    }
}
