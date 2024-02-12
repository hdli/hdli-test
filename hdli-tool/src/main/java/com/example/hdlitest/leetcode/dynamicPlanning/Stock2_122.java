package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/10 16:16
 */
public class Stock2_122 {

    /**
     * 买卖股票的最佳时机 II
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     *
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * 返回 你能获得的 最大 利润 。
     *
     * 相对与1的那道题，同一只股票可以多次购买，抛售后可以继续买
     * @param prices
     * @return
     */

    /**
     * 贪心算法：
     *
     * 贪心思路：这道题目可能我们只会想，选一个低的买入，再选个高的卖，再选一个低的买入.....循环反复。
     *      * 如果想到其实最终利润是可以分解的，那么本题就很容易了！
     *      * 假如第 0 天买入，第 3 天卖出，那么利润为：prices[3] - prices[0]。
     *      *
     *      * 相当于(prices[3] - prices[2]) + (prices[2] - prices[1]) + (prices[1] - prices[0])。
     *      * 此时就是把利润分解为每天为单位的维度，而不是从 0 天到第 3 天整体去考虑！
     *      *
     *      *  贪心只收集每天的正利润
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        for(int i = 1;i<prices.length;i++){
            result += Math.max(prices[i]-prices[i-1],0);
        }
        return result;
    }

    /**
     *
     * 和121. 买卖股票的最佳时机 (opens new window)唯一不同的地方，就是推导dp[i][0]的时候，第i天买入股票的情况。
     *
     * 在121. 买卖股票的最佳时机 (opens new window)中，因为股票全程只能买卖一次，所以如果买入股票，那么第i天持有股票即dp[i][0]一定就是 -prices[i]。
     *
     * 而本题，因为一只股票可以买卖多次，所以当第i天买入股票的时候，所持有的现金可能有之前买卖过的利润。
     *
     * 那么第i天持有股票即dp[i][0]，如果是第i天买入股票，所得现金就是昨天不持有股票的所得现金 减去 今天的股票价格 即：dp[i - 1][1] - prices[i]。
     *
     * @param prices
     * @return
     */
    public int maxProfitDp(int[] prices){
        int [][] dp = new int[prices.length][2];
        //初始化资金为0
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length;i++){
            //持有：上一天就持有，今天购买持有（因为可以多次购买，所有购买股票剩余现金：昨天未持有的钱-今天股票价格）
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]-prices[i]);
            //未持有：上一天就未持有，昨天持有今日卖掉未持有；
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]+prices[i]);
        }
        return dp[prices.length-1][1];
    }

}
