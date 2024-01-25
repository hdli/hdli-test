package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/1/26 00:19
 */
public class Base_UniquePathsWithObstacles_63 {

    /**
     * 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //如果在起点或终点出现了障碍，直接返回0
        if(obstacleGrid[0][0] == 1 & obstacleGrid[m-1][n-1] == 1){
            return 0;
        }

        int [][] dp = new int[m][n];
        for(int i = 0;i< m && obstacleGrid[i][0] != 1;i++){
            dp[i][0] = 1;
        }
        for(int i = 0;i<n && obstacleGrid[0][i] != 1;i++){
            dp[0][i] = 1;
        }
        for(int i = 1;i<m;i++){
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j]  = 0;
                }else{
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
