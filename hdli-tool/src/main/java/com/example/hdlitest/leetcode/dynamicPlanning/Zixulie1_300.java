package com.example.hdlitest.leetcode.dynamicPlanning;

import java.util.*;

/**
 * @author luyi
 * @date 2024/2/14 22:01
 */
public class Zixulie1_300 {

    /**
     * 最长递增子序列:给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     *
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     *
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        test(nums,0);
        return result;
    }

    /**
     * 暴力求解：回溯找子集
     */
    int result = 0;
    LinkedList<Integer> path = new LinkedList<>();
    private void test(int[] nums,int startIndex){
        if (!path.isEmpty()){
            result = Math.max(result,path.size());
        }
        Set<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            if ((!path.isEmpty() && nums[i] <= path.getLast()) || set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            path.add(nums[i]);
            test(nums,i+1);
            path.removeLast();
        }
    }

    /**
     * 动态规划:
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n)
     *
     * 1、dp[i]的定义
     * 本题中，正确定义dp数组的含义十分重要。
     *
     * dp[i]表示i之前包括i的以nums[i]结尾的最长递增子序列的长度
     *
     * 为什么一定表示 “以nums[i]结尾的最长递增子序” ，因为我们在 做 递增比较的时候，如果比较 nums[j] 和 nums[i] 的大小，那么两个递增子序列一定分别以nums[j]为结尾 和 nums[i]为结尾， 要不然这个比较就没有意义了，不是尾部元素的比较那么 如何算递增呢
     *
     * 2、状态转移方程
     * 位置i的最长升序子序列等于j从0到i-1各个位置的最长升序子序列 + 1 的最大值。
     *
     * 所以：if (nums[i] > nums[j]) dp[i] = max(dp[i], dp[j] + 1);
     * 注意这里不是要dp[i] 与 dp[j] + 1进行比较，而是我们要取dp[j] + 1的最大值。
     *
     * 3、dp[i]的初始化
     * 每一个i，对应的dp[i]（即最长递增子序列）起始大小至少都是1.
     *
     * 4、确定遍历顺序
     * dp[i] 是有0到i-1各个位置的最长递增子序列 推导而来，那么遍历i一定是从前向后遍历。
     *
     * j其实就是遍历0到i-1，那么是从前到后，还是从后到前遍历都无所谓，只要吧 0 到 i-1 的元素都遍历了就行了。 所以默认习惯 从前向后遍历。
     * @param nums
     * @return
     */
    public int lengthOfLISDp(int[] nums){
        int [] dp = new int[nums.length];
        Arrays.fill(dp,1);

        int result = 1;
        for (int i = 1; i < nums.length; i++){
            for (int j = 0; j < i;j++){
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                }
            }
            result = Math.max(result,dp[i]);
        }
        return result;
    }





}
