package com.example.hdlitest.leetcode.lianbiao;

/**
 * @author luyi
 * @date 2023/5/3 5:42 PM
 */
public class ReverseList_206 {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        ListNode node = ListNode.asNode(a);
        ListNode node1 = reverseList(node);
        node1.print();
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return recursion(head, head.next, true);
    }

    /**
     * 递归解法：每次操作两个节点，当前节点与下一个节点
     *
     * @param cur
     * @param next
     * @param isHead
     * @return
     */
    private static ListNode recursion(ListNode cur, ListNode next, Boolean isHead) {
        //递归结束条件
        if (next == null) {
            return cur;
        }
        if (isHead) {
            cur.next = null;
        }
        //先把下一个节点的下一个节点获取到
        ListNode next2 = next.next;
        //调转方向
        next.next = cur;
        return recursion(next, next2, false);
    }


    /**
     * 双指针
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        ListNode cur = head;
        //一开始就是null
        ListNode pre = null;
        while (cur != null){
            ListNode temp = cur.next;
            //当前节点转向
            cur.next = pre;
            //移动pre指针
            pre = cur;
            //移动cur指针
            cur = temp;
        }
        return pre;
    }

    /**
     * 根据双指针法，推导出的递归
     * @param cur
     * @param pre
     * @return
     */
    private ListNode recursion2(ListNode cur,ListNode pre){
        if (cur == null){
            return pre;
        }
        ListNode temp = cur.next;
        //转向
        cur.next = pre;
        //递归到下一层，相当于移动指针
        return recursion2(temp,cur);
    }
}
