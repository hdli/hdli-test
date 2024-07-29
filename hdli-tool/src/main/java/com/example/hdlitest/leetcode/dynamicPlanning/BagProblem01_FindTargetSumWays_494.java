package com.example.hdlitest.leetcode.dynamicPlanning;

import java.util.Arrays;

/**
 * @author luyi
 * @date 2024/1/28 18:21
 */
public class BagProblem01_FindTargetSumWays_494 {
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
    public static int findTargetSumWays(int[] nums, int target) {
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
        //问题到这里就变成了集合中所有元素 装满left有多少中方法
        int [] dp = new int[left+1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = left; j >= nums[i]; j--){
                dp[j] = dp[j]+dp[j-nums[i]];
            }
        }
        return dp[left];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1,1};
        int target = 3;
        int target1 = getTarget(nums, target);
        if (target1 == 0){
            return;
        }
        int targetSumWays =findTargetSumWays2(nums, target1);
        System.out.println(targetSumWays);

        System.out.println(findTargetSumWays(nums, target));
    }


    /**
     * 集合中，多少种可能 填满left
     * @param nums
     * @param target
     */
    private static int findTargetSumWays2(int[] nums, int target){

        int [][] dp = new int[nums.length][target+1];
        dp[0][0] = 1;
        if (nums[0] <= target){
            dp[0][nums[0]] = 1;
        }
        for (int i = 1;i < nums.length;i++){
            for (int j = 0;j <= target;j++){
                if (j < nums[i]){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]];
                }
            }
        }
        BagProblem_01.printDp(dp);
        return dp[nums.length-1][target];
    }


    private static int getTarget(int[] nums, int target){
        int sum = Arrays.stream(nums).sum();
        if ((sum-target)%2 == 1){
            return 0;
        }
        return  (sum-target)/2;
    }
}
