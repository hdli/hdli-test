package com.example.hdlitest.leetcode.dynamicPlanning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luyi
 * @date 2024/2/3 00:47
 */
public class BagProblemAll_NumSquares_279 {

    /**
     * 完全平方数:
     * 完全平方数就是物品（可以无限件使用），凑个正整数n就是背包，问凑满这个背包最少有多少物品？
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int [] dp = new int[n+1];
        dp[0] = 0;
        int max = Integer.MAX_VALUE;
        for (int i = 1; i < dp.length;i++){
            dp[i] = max;
        }

        //物品
        List<Integer> goods = new ArrayList<>();
        for (int i = 1; ;i++){
            int a = i*i;
            if (a > n){
                break;
            }
            goods.add(a);
        }
        for (int i = 0; i < goods.size();i++){
            for (int j = goods.get(i);j<= n;j++){
                if (dp[j-goods.get(i)] != max){
                    dp[j] = Math.min(dp[j],dp[j-goods.get(i)]+1);
                }
            }
        }
        return dp[n];
    }

    public int numSquares2(int n) {
        int [] dp = new int[n+1];
//        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        int max = Integer.MAX_VALUE;
        for (int i = 1; i < dp.length;i++){
            dp[i] = max;
        }
        // 遍历物品
        for (int i = 0; i*i <= n;i++){
            //遍历背包
            for (int j = i*i;j<= n;j++){
                if (dp[j-i*i] != max){
                    dp[j] = Math.min(dp[j],dp[j-i*i]+1);
                }
            }
        }
        return dp[n];
    }





}
