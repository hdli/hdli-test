package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * 完全背包
 * @author luyi
 * @date 2024/2/1 01:24
 */
public class BagProblemAll_Change_518 {
    /**
     * 零钱兑换 II：给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     *
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     *
     * 假设每一种面额的硬币有无限个。
     *
     *
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int [] dp = new int[amount+1];
        dp[0] = 1;
        for(int i = 0;i< coins.length;i++){
            for(int j = coins[i];j <= amount ;j++){
                dp[j] = dp[j] + dp[j-coins[i]];
            }
        }
        return dp[amount];
    }


    /**
     * 回溯方式  39题 组合总和
     * @param amount
     * @param coins
     * @return
     */
    public static int change2(int amount, int[] coins) {
        recall(amount,coins,0);
        return result;
    }

    static int result;

    private static void recall(int amount, int[] coins,int startIndex){
        if (amount == 0){
            result++;
            return;
        }
        for (int i = startIndex; i < coins.length;i++){
            if (coins[i] > amount){
                continue;
            }
            recall(amount - coins[i],coins,i);
        }
    }


    public static void main(String[] args) {
        int [] coins = new int[]{1,2,5};
        System.out.println(change2(5,coins));
    }



}
