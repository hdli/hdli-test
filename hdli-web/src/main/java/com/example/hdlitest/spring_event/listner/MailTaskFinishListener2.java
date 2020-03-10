package com.example.hdlitest.spring_event.listner;

import com.example.hdlitest.spring_event.event.TaskFinishEvent2;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义邮件服务监听器并向容器注册
 * 所有的事件监听器都必须向容器注册,容器能够对其进行识别并委托容器内真正的事件发布器进行管理
 * @author 李会东
 * @version 1.0
 * @date 2019-12-16 14:23
 */
@Component
public class MailTaskFinishListener2 implements ApplicationListener<TaskFinishEvent2> {

    private String emial="takumiCX@163.com";

    @Override
    public void onApplicationEvent(TaskFinishEvent2 event) {
        System.out.println("Send Emial to "+emial+" Task:"+event.getSource());
    }
}
