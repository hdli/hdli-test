package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/4 23:12
 */
public class Rob1_198 {


    /**
     * 打家劫舍:你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     *
     * 思路：
     * 1、确定dp数组（dp table）以及下标的含义
     * dp[i]：考虑下标i（包括i）以内的房屋，最多可以偷窃的金额为dp[i]。
     * 2、确定递推公式
     * 决定dp[i]的因素就是第i房间偷还是不偷。
     * 如果偷第i房间，那么dp[i] = dp[i - 2] + nums[i] ，即：第i-1房一定是不考虑的，找出 下标i-2（包括i-2）以内的房屋，最多可以偷窃的金额为dp[i-2] 加上第i房间偷到的钱。
     * 如果不偷第i房间，那么dp[i] = dp[i - 1]，即考 虑i-1房
     * 然后dp[i]取最大值，即dp[i] = max(dp[i - 2] + nums[i], dp[i - 1]);
     * 3、dp数组如何初始化
     * 从递推公式dp[i] = max(dp[i - 2] + nums[i], dp[i - 1]);可以看出，递推公式的基础就是dp[0] 和 dp[1]
     *
     * 从dp[i]的定义上来讲，dp[0] 一定是 nums[0]，dp[1]就是nums[0]和nums[1]的最大值即：dp[1] = max(nums[0], nums[1]);
     * 4、确定遍历顺序
     * dp[i] 是根据dp[i - 2] 和 dp[i - 1] 推导出来的，那么一定是从前到后遍历！
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        int [] dp = new int[nums.length+1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i = 2;i < nums.length ; i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length - 1];
    }


    /**
     * 使用滚动数组思想，优化空间
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        if (nums.length == 2){
            return Math.max(nums[0],nums[1]);
        }
        int a = nums[0];
        int b = Math.max(nums[0],nums[1]);
        int c = 0;
        for (int i = 2; i < nums.length;i++){
            c = Math.max(a+nums[i],b);
            a = b;
            b= c;
        }
        return c;
    }
}
