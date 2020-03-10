package com.example.hdlitest.designMode.java_event.publisher;

import com.example.hdlitest.designMode.java_event.event.AbstractEvent;
import com.example.hdlitest.designMode.java_event.listner.TaskFinishEventListner;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义事件发布器
 * @author 李会东
 * @version 1.0
 * @date 2019-12-16 11:30
 */
public class TaskFinishEventPublisher {

    private List<TaskFinishEventListner> listners=new ArrayList<>();

    //注册监听器
    public synchronized void register(TaskFinishEventListner listner){
        if(!listners.contains(listner)){
            listners.add(listner);
        }
    }

    //移除监听器
    public synchronized boolean remove(TaskFinishEventListner listner){
        return listners.remove(listner);
    }


    //发布任务结束事件
    public void publishEvent(AbstractEvent event){

        for(TaskFinishEventListner listner:listners){
            listner.onTaskFinish(event);
        }
    }
}
