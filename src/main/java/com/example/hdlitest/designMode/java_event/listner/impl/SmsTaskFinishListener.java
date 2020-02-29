package com.example.hdlitest.designMode.java_event.listner.impl;

import com.example.hdlitest.designMode.java_event.event.AbstractEvent;
import com.example.hdlitest.designMode.java_event.event.TaskFinishEvent;
import com.example.hdlitest.designMode.java_event.listner.TaskFinishEventListner;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 任务结束时将结果以短信的方式发送给用户,则可以再添加一个短信服务监听器
 * @author 李会东
 * @version 1.0
 * @date 2019-12-16 11:33
 */
@Data
@AllArgsConstructor
public class SmsTaskFinishListener implements TaskFinishEventListner<TaskFinishEvent> {

    private String mobile;

    @Override
    public void onTaskFinish(TaskFinishEvent event) {
        System.out.println("Send Message to "+ mobile +" Task:"+event.getSource());
    }
}
