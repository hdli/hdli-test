package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/4 23:35
 */
public class Rob2_213 {
    /**
     * 打家劫舍 II：  环
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     *
     *
     * 思路：将环形问题，变成线性问题
     * 1、考虑包含首元素，不包含尾元素
     * 2、考虑包含尾元素，不包含首元素
     * 3、考虑不包含首尾元素
     * 但是在这道题中其实1和2情况中已经包含了3的情况，所以只需要使用 打家劫舍1_198中的方法分别得到 1和2的值，求最大
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int deleteFirst = rob1(nums, 0, length - 1);
        int deleteLast = rob1(nums,1,length);
        return Math.max(deleteFirst, deleteLast);
    }


    public int rob1(int[] nums,int start, int end){
        int a = nums[start];
        int b = Math.max(nums[start + 1],nums[start]);
        int c = -1;
        for (int i = start+2; i < end;i++){
            c = Math.max(a+nums[i], b);
            a = b;
            b = c;
        }
        return c == -1 ? b : c;
    }

}
