package com.example.hdlitest.tool.guava.eventBus.spring;

import com.example.hdlitest.tool.guava.eventBus.spring.definition.Event;
import com.example.hdlitest.tool.guava.eventBus.spring.definition.EventListener;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *
 * 基于发布-订阅模式的异步事件总线
 * @author luyi
 * @date 2024/5/30 16:50
 */
@Component
public class AsyncEventBusFacade implements BeanPostProcessor {

    /**
     * 协作事件eventBus事件总线
     */
    private static final EventBus eventBus = new EventBus();


    /**
     * 注册监听器
     *
     * @param listener
     */
    public static void register(EventListener listener) {
        eventBus.register(listener);
    }

    /**
     * 通知事件
     *
     */
    public static void notify(Event event) {
        eventBus.post(event);
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> subscriberClazz = bean.getClass();
        Method[] methods = subscriberClazz.getMethods();
        for(Method method : methods) {
            if(method.isAnnotationPresent(Subscribe.class)){
                register((EventListener)bean);
                break;
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
