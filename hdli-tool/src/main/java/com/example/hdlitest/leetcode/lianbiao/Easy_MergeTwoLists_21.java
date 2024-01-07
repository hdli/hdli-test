package com.example.hdlitest.leetcode.lianbiao;

/**
 * @author luyi
 * @date 2024/1/6 16:54
 */
public class Easy_MergeTwoLists_21 {

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode temp = new ListNode(-1);
        ListNode current = temp;
        while(list1 != null || list2 != null){
            if(list1 == null){
                current.next = list2;
                break;
            }
            if(list2 == null){
                current.next = list1;
                break;
            }
            if(list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            }else{
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        return temp.next;
    }



    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        if (list1.val <= list2.val){
            list1.next = mergeTwoLists2(list1.next,list2);
            return list1;
        }else {
            list2.next = mergeTwoLists2(list1,list2.next);
            return list2;
        }
    }

}
