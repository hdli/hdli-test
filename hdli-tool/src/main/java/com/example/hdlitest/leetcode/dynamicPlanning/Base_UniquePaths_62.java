package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/1/25 23:56
 */
public class Base_UniquePaths_62 {

    /**
     * 不同路径:
     *
     * 动态规划5步曲
     *      * 1. 确定dp数组下标含义 dp[i][j] 到每一个坐标可能的路径种类
     *      * 2. 递推公式 dp[i][j] = dp[i-1][j] dp[i][j-1]
     *      * 3. 初始化 dp[i][0]=1 dp[0][j]=1 初始化横竖就可
     *      * 4. 遍历顺序 一行一行遍历
     *      * 5. 推导结果 。。。。。。。。
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        //确定dp数组下标含义 dp[i][j] 到每一个坐标可能的路径种类
        int [][] dp = new int[m][n];
        //初始化 dp[i][0]=1 dp[0][i]=1 初始化横竖就可
        for (int i = 0; i < m;i++){
            dp[i][0] = 1;
        }
        for (int i = 0;i<n;i++){
            dp[0][i] = 1;
        }
        // 遍历顺序 一行一行遍历
        for (int i = 1; i < m;i++){
            for (int j = 1;j< n;j++){
                //递推公式 dp[i][j] = dp[i-1][j] dp[i][j-1]
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
