package com.example.hdlitest.leetcode.lianbiao;

/**
 * @author luyi
 * @date 2023/5/20 17:00
 */
public class MyLinkedList_707 {

    int size;

    ListNode head;


    public MyLinkedList_707() {
        size = 0;
        head = new ListNode(0);
    }


    public int get(int index) {
        if (index < 0 || index >= size){
            return -1;
        }
        ListNode cur = head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    public void addAtTail(int val) {
        addAtIndex(size,val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size){
            return;
        }
        if (index < 0){
            index = 0;
        }
        size++;
        ListNode temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size){
            return;
        }
        size--;
        if (index == 0){
            head = head.next;
            return;
        }
        ListNode temp = head;
        //找到要删除元素的前一个元素 所以 i<index
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }
}
