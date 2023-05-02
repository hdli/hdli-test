package com.example.hdlitest.leetcode.lianbiao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *      * 给你一个链表的头节点 head ，判断链表中是否有环。
 *      *
 *      * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 *      *
 * @author luyi
 * @date 2023/4/30 5:15 PM
 */
public class HasCycle_141 {


    //性能不行
    public boolean hasCycle(ListNode head) {
        if (head == null){
            return false;
        }
        ListNode node = head;
        List<ListNode> list = new ArrayList<>();
        list.add(head);
        while (node != null){
            node =  node.next;
            if (list.contains(node)){
                return true;
            }
            list.add(node);
        }
        return false;
    }

    /**
     * 快慢指针, Floyd判圈算法
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * 找到进入循环点
     * @param head
     * @return
     */
    public ListNode hasCycle3(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                //先判断是右环的
                ListNode index1 = fast;
                ListNode index2 = head;
                while (!index1.equals(index2)){
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }

    /**
     * 计算环的长度
     * @param head
     * @return
     */
    public int cycleLength(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                //先判断是右环的
                ListNode index1 = fast.next;
                int length = 1;
                while (index1 != fast){
                    index1 =  index1.next;
                    length++;
                }
                return length;
            }
        }
        return 0;
    }

}
