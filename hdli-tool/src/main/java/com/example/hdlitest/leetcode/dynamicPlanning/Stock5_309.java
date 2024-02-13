package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/13 23:17
 */
public class Stock5_309 {

    /**
     *
     * 买卖股票的最佳时机含冷冻期:
     * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     *
     * 状态分4种
     * 状态一：持有股票状态（今天买入股票，或者是之前就买入了股票然后没有操作，一直持有）
     * 不持有股票状态，这里就有两种卖出股票状态
     *      状态二：保持卖出股票的状态（两天前就卖出了股票，度过一天冷冻期。或者是前一天就是卖出股票状态，一直没操作）
     *      状态三：今天卖出股票
     *      状态四：今天为冷冻期状态，但冷冻期状态不可持续，只有一天！
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        //0：状态一 持有股票
        //1：状态二 保持卖出股票
        //2：状态三 卖出
        //3：状态四 冷冻
        int [][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;
        for (int i = 1; i < prices.length;i++){
            //持有可由3中情况推出，1、前一天就已经持有，2、前一天是冷冻期，今天买入，3、前一天是保持卖出股票，今天买入
            dp[i][0] = Math.max(Math.max(dp[i-1][0],dp[i-1][3]-prices[i]),dp[i-1][1]-prices[i]);
            //保持卖出股票,可由2中情况推出，1、前一天就是，2、前一日是冷冻期
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][3]);
            //只有1种情况,昨天持有股票+今天股票的价格
            dp[i][2] = dp[i-1][0]+prices[i];
            //只有1种情况,昨天卖出股票
            dp[i][3] = dp[i-1][2];
        }
        //最后结果是取 状态二，状态三，和状态四的最大值
        return Math.max(Math.max(dp[prices.length-1][1],dp[prices.length-1][2]),dp[prices.length-1][3]);
    }
}
