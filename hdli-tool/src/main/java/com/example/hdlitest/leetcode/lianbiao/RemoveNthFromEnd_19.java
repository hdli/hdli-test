package com.example.hdlitest.leetcode.lianbiao;

/**
 * @author luyi
 * @date 2023/5/20 19:43
 */
public class RemoveNthFromEnd_19 {


    /**
     *
     * 快慢指针的间隔是n,当快指针走到最后，那么慢指针所在位置就是要删除的节点
     * 删除某个节点，需要先找到它前一个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //增加虚拟头节点，这样就不需要特判头节点的处理逻辑了
        ListNode temp = new ListNode(-1,head);
        ListNode fast = temp;
        ListNode slow = temp;

        while (fast.next != null){
            fast = fast.next;
            n-=1;
            if (n <= -1){
                slow = slow.next;
            }
        }
        if (slow.next != null){
            slow.next = slow.next.next;
        }
        return temp.next;
    }
}
