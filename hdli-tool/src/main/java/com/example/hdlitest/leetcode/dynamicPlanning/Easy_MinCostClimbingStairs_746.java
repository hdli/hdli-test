package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/1/25 01:27
 */
public class Easy_MinCostClimbingStairs_746 {

    /**
     * 使用最小花费爬楼梯:给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     *
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     *
     * 请你计算并返回达到楼梯顶部的最低花费。
     *
     *
     * 1、确定dp数组以及下标的含义
     *  使用动态规划，就要有一个数组来记录状态，本题只需要一个一维数组dp[i]就可以了。
     *
     *  dp[i]的定义：到达第i台阶所花费的最少体力为dp[i]。
     *
     *  对于dp数组的定义，大家一定要清晰！
     * 2、确定递推公式
     *  可以有两个途径得到dp[i]，一个是dp[i-1] 一个是dp[i-2]。
     *
     *  dp[i - 1] 跳到 dp[i] 需要花费 dp[i - 1] + cost[i - 1]。
     *
     *  dp[i - 2] 跳到 dp[i] 需要花费 dp[i - 2] + cost[i - 2]。
     *
     *  那么究竟是选从dp[i - 1]跳还是从dp[i - 2]跳呢？
     *
     *  一定是选最小的，所以dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
     * 3、dp数组如何初始化
     *  新题目描述中明确说了 “你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。” 也就是说 到达 第 0 个台阶是不花费的，但从 第0 个台阶 往上跳的话，需要花费 cost[0]。
     *
     *  所以初始化 dp[0] = 0，dp[1] = 0;
     * 4、确定遍历顺序
     *  最后一步，递归公式有了，初始化有了，如何遍历呢？
     *
     *  本题的遍历顺序其实比较简单，简单到很多同学都忽略了思考这一步直接就把代码写出来了。
     *
     *  因为是模拟台阶，而且dp[i]由dp[i-1]dp[i-2]推出，所以是从前到后遍历cost数组就可以了。
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int [] dp = new int[len+1];

        // 从下标为 0 或下标为 1 的台阶开始，因此支付费用为0
        dp[0] = 0;
        dp[1] = 0;
        // 计算到达每一层台阶的最小费用
        for(int i =2;i<=len;i++){
            dp[i]=Math.min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2]);
        }
        return dp[len];
    }
}
