package com.example.hdlitest.designMode.java_event.event;

import com.example.hdlitest.designMode.java_event.constant.TaskFinishStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * 任务结束事件的事件源
 * 因为要对任务执行结束这一事件进行监听,所以必须对任务这一概念进行定义
 *
 * @author 李会东
 * @version 1.0
 * @date 2019-12-16 11:23
 */
@Data
@AllArgsConstructor
public class Task {
    private String name;

    private TaskFinishStatus status;


}
