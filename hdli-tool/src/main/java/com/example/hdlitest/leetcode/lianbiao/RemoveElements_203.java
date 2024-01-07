package com.example.hdlitest.leetcode.lianbiao;

/**
 * @author luyi
 * @date 2023/4/27 12:16 AM
 */
public class RemoveElements_203 {

    /**
     * 删除指定节点，
     * 需要从 上一个节点开始判断
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return null;
        }
        //为了判断头节点与普通节点逻辑一致， 构建虚拟头节点
        ListNode virtualNode = new ListNode(1,head);

        ListNode currentNode = virtualNode;
        while(currentNode.next != null){
            if (currentNode.next.val == val){
                currentNode.next = currentNode.next.next;
            }else {
                currentNode = currentNode.next;
            }
        }
        return virtualNode.next;
    }





    public ListNode removeElements2(ListNode head, int val) {
        if (head == null){
            return null;
        }
        //处理非头节点
        ListNode parentNode = head;
        ListNode currentNode = head.next;
        while (currentNode != null){
            if (currentNode.val == val){
                //删除节点
                parentNode.next = currentNode.next;
                //只需要移动当前节点
                currentNode = currentNode.next;
            }else {
                parentNode = currentNode;
                currentNode = currentNode.next;
            }
        }
        //删头节点
        if (head.val == val){
            return head.next;
        }
        return head;
    }

}
