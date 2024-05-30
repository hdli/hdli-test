package com.example.hdlitest.tool.guava.eventBus.spring;

import com.example.hdlitest.tool.guava.eventBus.spring.definition.Event;

/**
 *
 * 定义同步ODPS完成事件
 * @author luyi
 * @date 2024/5/30 16:36
 */
public class OdpsSycCompleteEvent implements Event {
    private String odpsTableName;

    private String datePartition;

    private Long groupId;

    public OdpsSycCompleteEvent(String odpsTableName, String datePartition, Long groupId) {
        super();
        this.odpsTableName = odpsTableName;
        this.datePartition = datePartition;
        this.groupId = groupId;
    }
}
