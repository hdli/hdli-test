package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/10 15:21
 */
public class Stock1_121 {


    /**
     * 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     *
     *
     * @param prices
     * @return
     */

    /**
     * 暴力方式:找最优间距了
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices){
        int res = 0;
        for (int i = 0; i < prices.length;i++){
            for (int j = i+1;j<prices.length;j++){
                res = Math.max(res, prices[j]-prices[i]);
            }
        }
        return res;
    }

    /**
     * 因为股票就买卖一次，那么贪心的想法很自然就是取最左最小值，取最右最大值，那么得到的差值就是最大利润。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param prices
     * @return
     */
    public int maxProfitGreed(int[] prices){
        //维护从左找最小值
        int minValue = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < prices.length;i++){
            //先找最小值
            minValue = Math.min(minValue,prices[i]);
            res = Math.max(res,prices[i]-minValue);
        }
        return res;
    }

    /**
     * 1、确定dp数组（dp table）以及下标的含义
     * dp[i][0] 表示第i天持有股票所得最多现金，这里可能有同学疑惑，本题中只能买卖一次，持有股票之后哪还有现金呢？
     *
     * 其实一开始现金是0，那么加入第i天买入股票现金就是 -prices[i]， 这是一个负数。
     *
     * dp[i][1] 表示第i天不持有股票所得最多现金
     * 注意这里说的是“持有”，“持有”不代表就是当天“买入”！也有可能是昨天就买入了，今天保持持有的状态
     *
     * 2、确定递推公式
     * 如果第i天持有股票即dp[i][0]， 那么可以由两个状态推出来
     * .第i-1天就持有股票，那么就保持现状，所得现金就是昨天持有股票的所得现金 即：dp[i - 1][0]
     * .第i天买入股票，只能买一次，所以初始化资金就是0，所得现金就是买入今天的股票后所得现金即：-prices[i]
     * 那么dp[i][0]应该选所得现金最大的，所以dp[i][0] = max(dp[i - 1][0], -prices[i]);
     *
     * 如果第i天不持有股票即dp[i][1]， 也可以由两个状态推出来
     * .第i-1天就不持有股票，那么就保持现状，所得现金就是昨天不持有股票的所得现金 即：dp[i - 1][1]
     * .第i天卖出股票，所得现金就是按照今天股票价格卖出后所得现金即：prices[i] + dp[i - 1][0]
     *
     * 同样dp[i][1]取最大的，dp[i][1] = max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
     *
     * 3、dp数组如何初始化
     * 由递推公式 dp[i][0] = max(dp[i - 1][0], -prices[i]);
     *          dp[i][1] = max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
     * 可以看出，其基础都是要从dp[0][0]和dp[0][1]推导出来；
     * 那么dp[0][0]表示第0天持有股票，此时的持有股票就一定是买入股票了，因为不可能有前一天推出来，所以dp[0][0] -= prices[0];
     * dp[0][1]表示第0天不持有股票，不持有股票那么现金就是0，所以dp[0][1] = 0;
     *
     * 4、确定遍历顺序
     * 从递推公式可以看出dp[i]都是由dp[i - 1]推导出来的，那么一定是从前向后遍历。
     *
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param prices
     * @return
     */
    public int maxProfitDp(int[] prices){
        int [][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length;i++){
            //持有：上一天就持有，今天购买持有（因为只能买一次，初始化资金就是0，所有购买股票剩余现金0-prices[i]）
            dp[i][0] = Math.max(dp[i-1][0],-prices[i]);
            //未持有：上一天就未持有，昨天持有今日卖掉未持有；
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]+prices[i]);
        }
//        return Math.max(dp[prices.length-1][0],dp[prices.length-1][1]);
        //求最大利润，一定是未持有，都卖掉后的利润最高
        return dp[prices.length-1][1];
    }
}
