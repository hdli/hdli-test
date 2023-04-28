package com.example.hdlitest.leetcode.lianbiao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyi
 * @date 2023/4/27 12:29 AM
 */
public class MergeTwoLists_21 {


    /**
     * 优秀
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        // 类似归并排序中的合并过程
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }

        return dummyHead.next;
    }



    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        t(list,list1);
        t(list,list2);
        list.sort(Integer::compareTo);
        ListNode headNode = new ListNode(list.get(0));
        ListNode current = headNode;
        for (int i = 1;i < list.size();i++){
            ListNode node = new ListNode(list.get(i));
            current.next = node;
            current = node;
        }

        return headNode;
    }

    private void t(List<Integer> list,ListNode list1){
        if (list1 != null){
            list.add(list1.val);
            t(list,list1.next);
        }
    }
}
