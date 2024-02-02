package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/3 00:36
 */
public class BagProblemAll_CoinChange_322 {


    /**
     * 零钱兑换:给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     *
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     *
     *
     * 1、确定dp数组以及下标的含义
     * dp[j]：凑足总额为j所需钱币的最少个数为dp[j]
     *
     * 2、确定递推公式
     * 凑足总额为j - coins[i]的最少个数为dp[j - coins[i]]，那么只需要加上一个钱币coins[i]即dp[j - coins[i]] + 1就是dp[j]（考虑coins[i]）
     *
     * 所以dp[j] 要取所有 dp[j - coins[i]] + 1 中最小的。
     *
     * 递推公式：dp[j] = min(dp[j - coins[i]] + 1, dp[j]);
     *
     * 3、dp数组如何初始化
     * 首先凑足总金额为0所需钱币的个数一定是0，那么dp[0] = 0;
     *
     * 其他下标对应的数值呢？
     *
     * 考虑到递推公式的特性，dp[j]必须初始化为一个最大的数，否则就会在min(dp[j - coins[i]] + 1, dp[j])比较的过程中被初始值覆盖。
     *
     *
     *4、确定遍历顺序
     * 本题求钱币最小个数，那么钱币有顺序和没有顺序都可以，都不影响钱币的最小个数。
     *
     * 所以本题并不强调集合是组合还是排列。
     *
     * 所以下标非0的元素都是应该是最大值。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[amount + 1];
        //初始化dp数组为最大值
        for (int j = 0; j < dp.length; j++) {
            dp[j] = max;
        }
        //当金额为0时需要的硬币数目为0
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            //正序遍历：完全背包每个硬币可以选择多次
            for (int j = coins[i]; j <= amount; j++) {
                //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                if (dp[j - coins[i]] != max) {
                    //选择硬币数目最小的情况
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }
}
