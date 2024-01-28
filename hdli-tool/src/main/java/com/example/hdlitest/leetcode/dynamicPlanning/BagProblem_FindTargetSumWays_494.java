package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/1/28 18:21
 */
public class BagProblem_FindTargetSumWays_494 {
    /**
     *  目标和:给你一个非负整数数组 nums 和一个整数 target 。
     *
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     *
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     *
     * +号 部分 总和 left,  -号部分 总和 right  那么
     * left+right = sum
     * left-right = target
     * left = (sum+target)/2
     * 问题到这里就变成了集合中所有元素 装满left有多少中方法？每个元素只能用一次
     * 01背包问题
     *
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        //+号 部分 总和 left,  -号部分 总和 right  那么
        // left+right = sum
        // left-right = target
        // left = (sum+target)/2
        int sum = 0;
        for (int i : nums){
            sum+=i;
        }
        //如果target过大 sum将无法满足
        if(target < 0 && sum < -target){
            return 0;
        }
        //数组中都是非负整数，不可能 拼出小数 left = (sum+target)/2
        if ((target+sum)%2 == 1){
            return 0;
        }
        int left = (target+sum)/2;
        //target 可能是负数
        if (left < 0){
            left = -left;
        }
        //
        int [] dp = new int[left+1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = left; j >= nums[i]; j--){
                dp[j] = dp[j]+dp[j-nums[i]];
            }
        }
        return dp[left];
    }
}
