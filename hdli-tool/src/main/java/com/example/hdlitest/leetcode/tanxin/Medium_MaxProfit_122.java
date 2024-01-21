package com.example.hdlitest.leetcode.tanxin;

/**
 * @author luyi
 * @date 2024/1/20 00:22
 */
public class Medium_MaxProfit_122 {

    /**
     * 买卖股票的最佳时机 II:
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     *
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * 返回 你能获得的 最大 利润 。
     *
     *
     * 贪心思路：这道题目可能我们只会想，选一个低的买入，再选个高的卖，再选一个低的买入.....循环反复。
     * 如果想到其实最终利润是可以分解的，那么本题就很容易了！
     * 假如第 0 天买入，第 3 天卖出，那么利润为：prices[3] - prices[0]。
     *
     * 相当于(prices[3] - prices[2]) + (prices[2] - prices[1]) + (prices[1] - prices[0])。
     *此时就是把利润分解为每天为单位的维度，而不是从 0 天到第 3 天整体去考虑！
     *
     *  贪心只收集每天的正利润
     *  把每一天的利润都算出来 prices[i]-prices[i-1]，正的加起来 就是最大利润
     *   7   1   5    3    6     4
     *    -6   4   -2    3    -2
     *
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        for(int i = 1;i<prices.length;i++){
            //贪心只收集每天的正利润
            result += Math.max(prices[i]-prices[i-1],0);
        }
        return result;
    }
}
