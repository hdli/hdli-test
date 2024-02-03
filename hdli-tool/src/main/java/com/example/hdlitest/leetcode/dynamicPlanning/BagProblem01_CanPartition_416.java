package com.example.hdlitest.leetcode.dynamicPlanning;

import java.util.Arrays;

/**
 * @author luyi
 * @date 2024/1/28 16:17
 */
public class BagProblem01_CanPartition_416 {


    public static void main(String[] args) {
        int [] a = {1,5,11,5};
        BagProblem01_CanPartition_416 canPartition416 = new BagProblem01_CanPartition_416();
        boolean b = canPartition416.canPartition(a);
        System.out.println(b);
    }

    /**
     * 分割等和子集:给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     *
     *  暴力解法：回溯,在数组中找组成数组总和一半的数
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int a = sum%2;
        if (a != 0){
            //正整数 的 非空 数组 ,数组总和是奇数，那肯定返回false
            return false;
        }
        int target = sum/2;
        return test(nums,target,0);
    }

    private boolean test(int[] nums,int target,int startIndex){
        if (target == 0){
            return true;
        }
        for (int i = startIndex; i <nums.length;i++){
            if (nums[i] > target){
                continue;
            }
            target-=nums[i];
            boolean test = test(nums, target, i + 1);
            if (test){
                return true;
            }
            target+=nums[i];
        }
        return false;
    }


    /**
     * 动态规划 : 01背包问题
     * 只有确定了如下四点，才能把01背包问题套到本题上来。
     *
     * 1、背包的体积为sum / 2
     * 2、背包要放入的商品（集合里的元素）重量为 元素的数值，价值也为元素的数值
     * 3、背包如果正好装满，说明找到了总和为 sum / 2 的子集。
     * 3、背包中每一个元素是不可重复放入。
     * @param nums
     * @return
     */
    public boolean canPartitioDp(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int a = sum%2;
        if (a != 0){
            //正整数 的 非空 数组 ,数组总和是奇数，那肯定返回false
            return false;
        }
        int target = sum/2;

        int [] dp = new int[target+1];
        for (int i = 0; i < nums.length;i++){
            for (int j = target;j >= nums[i];j--){
                dp[j] = Math.max(dp[j],dp[j-nums[i]]+nums[i]);
            }
            //重量与价值都是nums[i]
            if (dp[target] == target){
                return true;
            }
        }
        return dp[target] == target;
    }

    public boolean canPartitioDp2(int[] nums){
        int sum = Arrays.stream(nums).sum();
        int a = sum%2;
        if (a != 0){
            //正整数 的 非空 数组 ,数组总和是奇数，那肯定返回false
            return false;
        }
        int target = sum/2;
        boolean [] dp = new boolean[target+1];
        //初始化第0行
        dp[0] = true;
        if (nums[0] <= target){
            dp[nums[0]] = true;
        }
        //从第1行开始遍历
        for (int i = 1; i < nums.length;i++){
            //倒着遍历，因为当前行遍历依赖上一行左边的值
            for (int j = target;j >= nums[i];j--){
                dp[j] = dp[j] || dp[j-nums[i]];
            }
            if (dp[target]){
                return true;
            }
        }
        return dp[target];
    }


}
