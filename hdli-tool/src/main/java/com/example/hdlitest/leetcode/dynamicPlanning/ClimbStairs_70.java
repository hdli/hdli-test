package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2023/5/3 5:32 PM
 */
public class ClimbStairs_70 {

    /**
     *
     * 1阶 1
     * 2阶 2
     * 3阶 3
     * 4阶 5
     * 5阶 8
     *
     * n阶 = n-1阶 + n-2阶
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        //下标i为楼梯层数，dp[i]为方法数
        int [] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public int climbStairs2(int n) {
        if(n <= 2){
            return n;
        }
        int a = 1;
        int b = 2;
        int sum = 0;
        for(int i = 3 ;i<= n;i++){
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
}
