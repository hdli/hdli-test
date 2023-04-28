package com.example.hdlitest.leetcode.lianbiao;

/**
 * @author luyi
 * @date 2023/4/27 12:28 AM
 */
public class ListNode {

    int val;

    ListNode next;

    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }


    public void print () {
        ListNode curNode = this;
        while (curNode != null) {
            System.out.print(curNode.val+",");
            curNode = curNode.next;
        }
    }


    public static ListNode asNode (int ... nums) {
        ListNode head = new ListNode(nums[0]);
        ListNode curNode = head;
        for (int i = 1; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            curNode.next = node;
            curNode = node;
        }
        return head;
    }
}
