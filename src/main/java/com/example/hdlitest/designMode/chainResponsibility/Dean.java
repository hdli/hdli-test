package com.example.hdlitest.designMode.chainResponsibility;

/**
 * 院长
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 16:54
 */
public class Dean extends AbstractLeader {
    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 10) {
            System.out.println("院长批准您请假" + leaveDays + "天。");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(leaveDays);
            } else {
                System.out.println("请假天数太多，没有人批准该假条！");
            }
        }
    }
}
