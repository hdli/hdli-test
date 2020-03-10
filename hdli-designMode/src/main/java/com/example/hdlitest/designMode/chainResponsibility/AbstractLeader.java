package com.example.hdlitest.designMode.chainResponsibility;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 16:51
 */
public abstract class AbstractLeader {
    private AbstractLeader next;

    public void setNext(AbstractLeader next) {
        this.next = next;
    }

    public AbstractLeader getNext() {
        return next;
    }

    /**
     * 处理请求的方法
     */
    public abstract void handleRequest(int leaveDays);
}
