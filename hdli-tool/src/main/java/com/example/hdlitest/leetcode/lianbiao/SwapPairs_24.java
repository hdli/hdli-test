package com.example.hdlitest.leetcode.lianbiao;

/**
 * @author luyi
 * @date 2023/5/20 18:13
 */
public class SwapPairs_24 {

    /**
     *
     * 两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode temp = new ListNode(0,head);
        ListNode cur = temp;
        while (cur.next != null && cur.next.next != null){
            ListNode next = cur.next;
            ListNode nextNext = cur.next.next;
            cur.next = nextNext;
            next.next= nextNext.next;
            nextNext.next = next;
            //指针节点移动
            cur = next;
        }
        return temp.next;
    }
}
