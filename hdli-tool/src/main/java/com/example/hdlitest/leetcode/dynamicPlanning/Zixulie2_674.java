package com.example.hdlitest.leetcode.dynamicPlanning;

import java.util.Arrays;

/**
 * @author luyi
 * @date 2024/2/14 23:23
 */
public class Zixulie2_674 {
    /**
     * 最长连续递增序列 简单题
     * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
     *
     * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     *
     * 输入：nums = [1,3,5,4,7]
     * 输出：3
     * 解释：最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
     *
     *
     *
     *
     *  贪心算法
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        // 连续子序列最少也是1
        int result = 1;
        //记录某一次连续子序列的长度，一旦出现原属不是递增的，重新开始计数
        int a = 1;
        for(int i = 1;i< nums.length;i++){
            if(nums[i] > nums[i-1]){
                a+=1;
                result = Math.max(result,a);
            }else{
                a=1;
            }
        }
        return result;
    }

    /**
     * 动态规划思路:本题相对于昨天的动态规划：300.最长递增子序列 (opens new window)最大的区别在于“连续”。
     * @param nums
     * @return
     */
    public int findLengthOfLCISDp(int[] nums){
        int [] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int res = 1;
        for (int i = 1; i< nums.length;i++){
            if (nums[i] > nums[i-1]){
                dp[i] = dp[i-1]+1;
            }
            if (dp[i] > res){
                res = dp[i];
            }
        }
        return res;
    }



}
