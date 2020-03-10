package com.example.hdlitest.designMode.java_event;

import com.example.hdlitest.designMode.java_event.constant.TaskFinishStatus;
import com.example.hdlitest.designMode.java_event.event.AbstractEvent;
import com.example.hdlitest.designMode.java_event.event.Task;
import com.example.hdlitest.designMode.java_event.event.TaskFinishEvent;
import com.example.hdlitest.designMode.java_event.listner.impl.MailTaskFinishListener;
import com.example.hdlitest.designMode.java_event.listner.impl.SmsTaskFinishListener;
import com.example.hdlitest.designMode.java_event.publisher.TaskFinishEventPublisher;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-16 11:31
 */
public class TestTaskFinishListener {

    public static void main(String[] args) {
        //事件源
        Task source = new Task("用户统计", TaskFinishStatus.SUCCEDD);

        //任务结束事件
        AbstractEvent event = new TaskFinishEvent(source);

        //邮件服务监听器
        MailTaskFinishListener mailListener = new MailTaskFinishListener("takumiCX@163.com");
        //短信服务监听器
        SmsTaskFinishListener smsListener = new SmsTaskFinishListener("123456789");

        //事件发布器
        TaskFinishEventPublisher publisher = new TaskFinishEventPublisher();

        //注册邮件服务监听器
        publisher.register(mailListener);
        publisher.register(smsListener);

        //发布事件
        publisher.publishEvent(event);
    }
}
