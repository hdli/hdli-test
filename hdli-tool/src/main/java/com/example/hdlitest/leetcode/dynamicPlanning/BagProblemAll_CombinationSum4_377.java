package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/2 00:57
 */
public class BagProblemAll_CombinationSum4_377 {

    public static void main(String[] args) {
        int [] a = {1,2,3};
        BagProblemAll_CombinationSum4_377 combinationSum4377 = new BagProblemAll_CombinationSum4_377();
        int i = combinationSum4377.combinationSum4(a, 4);
        System.out.println(i);
    }

    /**
     * 组合总和 Ⅳ：给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        test(nums,target,0);
        return result;
    }

    Integer result = 0;
    /**
     * 暴力破解 ： 回溯
     * @param nums
     * @param target
     * @param sum
     */
    private void test(int[] nums,int target,int sum){
        if (sum == target){
            result+=1;
            return;
        }
        if (sum > target){
            return;
        }
        for (int i = 0; i < nums.length ; i++){
            test(nums,target,sum+nums[i]);
        }
    }


    /**
     * 动态规划：本地和零钱兑换II_518非常像
     * 回溯算法：39.组合总和 (opens new window)和回溯算法：40.组合总和II (opens new window)会感觉这两题和本题很像！
     *
     * 本题题目描述说是求组合，但又说是可以元素相同顺序不同的组合算两个组合，其实就是求排列！
     * 在动态规划：494.目标和 (opens new window)和 动态规划：518.零钱兑换II (opens new window)中我们已经讲过了，求装满背包有几种方法，递推公式一般都是dp[i] += dp[i - nums[j]];
     *
     * 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
     *
     * 如果求排列数就是外层for遍历背包，内层for循环遍历物品。
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum42(int[] nums, int target) {
        int [] dp = new int[target+1];
        dp[0] = 1;
        for(int j = 0;j<= target;j++){
            for(int i = 0;i<nums.length;i++){
                if(j >= nums[i]){
                    dp[j] += dp[j-nums[i]];
                }
            }
        }
        return dp[target];
    }

}
