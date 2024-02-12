package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/12 23:15
 */
public class Stock4_188 {

    /**
     * 买卖股票的最佳时机 IV 困难题
     *
     * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int [][] dp = new int[prices.length][2*k+1];
        dp[0][0] = 0;
        //0 表示不操作
        //1 第一次买入
        //2 第一次卖出
        //3 第二次买入
        //4 第二次卖出
        //......
        for (int j = 1;j< 2*k;j+=2){
            //买入
            dp[0][j] = -prices[0];
            //卖出
            dp[0][j+1] = 0;
        }
        for (int i = 1; i < prices.length; i++) {
            //不操作
            dp[i][0] = dp[i-1][0];
            for (int j = 1;j < 2*k;j+=2){
                //买入
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]-prices[i]);
                //卖出
                dp[i][j+1] = Math.max(dp[i-1][j+1],dp[i-1][j]+prices[i]);
            }
        }
        return dp[prices.length - 1][2*k];
    }
}
