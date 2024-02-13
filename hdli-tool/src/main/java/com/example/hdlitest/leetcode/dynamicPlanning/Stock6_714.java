package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/14 00:07
 */
public class Stock6_714 {

    /**
     * 买卖股票的最佳时机含手续费:
     * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     *
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     *
     * 返回获得利润的最大值。
     *
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     *
     *
     * 思路：
     * 相对于动态规划：122.买卖股票的最佳时机II，本题只需要在计算卖出操作的时候减去手续费就可以了，代码几乎是一样的。
     * 每次卖出股票时，减去手续费
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int [][] dp = new int[prices.length][2];
        //初始化资金为0
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length;i++){
            //持有：上一天就持有，今天购买持有（因为可以多次购买，所有购买股票剩余现金：昨天未持有的钱-今天股票价格）
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]-prices[i]);
            //未持有：上一天就未持有，昨天持有今日卖掉未持有； 每次卖出股票时，减去手续费
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]+prices[i] - fee);
        }
        return dp[prices.length-1][1];
    }
}
