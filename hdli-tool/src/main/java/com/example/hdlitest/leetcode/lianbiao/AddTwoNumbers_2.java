package com.example.hdlitest.leetcode.lianbiao;

/**
 * @author luyi
 * @date 2023/5/31 22:33
 */
public class AddTwoNumbers_2 {


    /**
     * 两数相加
     *
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     *
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //进位
        int flag = 0;

        ListNode result = new ListNode(-1);
        ListNode resultTemp = result;
        while(l1 != null || l2 != null){
            int l1Vaule = 0;
            if(l1 != null){
                l1Vaule = l1.val;
                l1 = l1.next;
            }
            int l2Vaule = 0;
            if(l2 != null){
                l2Vaule = l2.val;
                l2 = l2.next;
            }

            int curVal = l1Vaule+l2Vaule+flag;
            if(curVal >= 10){
                curVal = curVal-10;
                flag = 1;
            }else{
                flag = 0;
            }
            ListNode resultCur = new ListNode(curVal);

            resultTemp.next = resultCur;
            resultTemp = resultCur;
        }
        //如果遍历结束了 进位为1 ，那在结果链表后还需要加一个节点，值为1
        if(flag == 1){
            resultTemp.next = new ListNode(1);
        }
        return result.next;
    }
}
