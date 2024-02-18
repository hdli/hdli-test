package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/16 22:30
 */
public class Zixulie6_53 {


    /**
     * 最大子数组和:给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 子数组 是数组中的一个连续部分。
     *
     * 本题可以使用贪心方式：Medium_MaxSubArray_53
     *
     *
     * 动态规划思路：
     * 1、确定dp数组（dp table）以及下标的含义
     * dp[i]：包括下标i（以nums[i]为结尾）的最大连续子序列和为dp[i]。
     *
     * 2、确定递推公式
     * dp[i]只有两个方向可以推出来：
     *  +dp[i - 1] + nums[i]，即：nums[i]加入当前连续子序列和
     *  +nums[i]，即：从头开始计算当前连续子序列和
     * 一定是取最大的，所以dp[i] = max(dp[i - 1] + nums[i], nums[i]);
     *
     * 3、dp数组如何初始化
     * 从递推公式可以看出来dp[i]是依赖于dp[i - 1]的状态，dp[0]就是递推公式的基础。
     * 根据dp[i]的定义，很明显dp[0]应为nums[0]即dp[0] = nums[0]。
     *
     * 4、确定遍历顺序
     * 递推公式中dp[i]依赖于dp[i - 1]的状态，需要从前向后遍历。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int [] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length ;i++){
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            if (dp[i] > result){
                result = dp[i];
            }
        }
        return result;
    }


    //因为dp[i]的递推公式只与前一个值有关，所以可以用一个变量代替dp数组，空间复杂度为O(1)
    public int maxSubArray2(int[] nums) {
        //上一个 相当于 dp[i-1]
        int pre = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //当前计算所得，相当于dp[i]
            int curr = Math.max(pre+nums[i],nums[i]);
            if (curr > result){
                result = curr;
            }
            //下一个计算中 dp[i] 变成了dp[i-1]
            pre = curr;
        }
        return result;
    }
}
