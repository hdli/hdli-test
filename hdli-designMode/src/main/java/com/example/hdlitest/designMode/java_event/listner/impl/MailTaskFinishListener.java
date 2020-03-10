package com.example.hdlitest.designMode.java_event.listner.impl;

import com.example.hdlitest.designMode.java_event.event.TaskFinishEvent;
import com.example.hdlitest.designMode.java_event.listner.TaskFinishEventListner;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 邮件服务监听器
 * 该邮件服务监听器将在监听到任务结束事件时将任务的执行结果发送给用户
 * @author 李会东
 * @version 1.0
 * @date 2019-12-16 11:27
 */
@Data
@AllArgsConstructor
public class MailTaskFinishListener implements TaskFinishEventListner<TaskFinishEvent> {
    /**
     * 可以是用户集合
     */
    private String emial;

    @Override
    public void onTaskFinish(TaskFinishEvent event) {
        System.out.println("Send Emial to "+emial+" Task:"+event.getSource());
    }
}
