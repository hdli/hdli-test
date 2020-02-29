package com.example.hdlitest.designMode.spring_event;

import com.example.hdlitest.designMode.spring_event.event.TaskFinishEvent2;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 李会东
 * @version 1.0
 * @date 2020-1-6 18:11
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册监听器
        context.addApplicationListener((ApplicationListener<TaskFinishEvent2>) event -> {
            System.out.println("接受事件的消息："+event.getSource());
        });
        context.refresh();
        //发布事件
        context.publishEvent(new TaskFinishEvent2("Hello,World"));
    }
}
