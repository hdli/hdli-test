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

}
