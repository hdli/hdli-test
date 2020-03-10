package com.example.hdlitest.designMode.chainResponsibility;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 16:55
 */
public class LeaveApprovalTest {
    /**
     * 职责链模式主要包含以下角色。
     *
     *     抽象处理者（Handler）角色：定义一个处理请求的接口，包含抽象处理方法和一个后继连接。
     *     具体处理者（Concrete Handler）角色：实现抽象处理者的处理方法，判断能否处理本次请求，如果可以处理请求则处理，否则将该请求转给它的后继者。
     *     客户类（Client）角色：创建处理链，并向链头的具体处理者对象提交请求，它不关心处理细节和请求的传递过程。
     * @param args
     */
    public static void main(String[] args) {
        //组装责任链
        AbstractLeader teacher1=new ClassAdviser();
        AbstractLeader teacher2=new DepartmentHead();
        AbstractLeader teacher3=new Dean();
        teacher1.setNext(teacher2);
        teacher2.setNext(teacher3);

        teacher1.handleRequest(8);
    }
}
