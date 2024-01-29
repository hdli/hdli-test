package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/1/30 00:20
 */
public class BagProblem_FindMaxForm_474 {

    /**
     * 一和零:
     *
     * 这道题其实是压缩后的写法，滚动数组
     *
     * 一种是不进行数组压缩：这时dp数组应该是dp[length + 1][m + 1][n + 1],
     * 第二种是把i进行压缩，dp数组的大小就是dp【m+1】【n+1】，这时二维的背包就可以变成正常的二维矩阵模式。
     * m和n分别是子集中0和1允许的最大个数。不过两种方式的背包遍历方式是反过来的。
     * 由第一种推出第二种比较符合一般的01背包的思考逻辑。
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        //dp[i][j]表示i个0和j个1时的最大子集
        int [][] dp = new int[m+1][n+1];
        int zeroNum,oneNum;
        for (String str : strs){
            zeroNum = 0;
            oneNum = 0;
            for(char c : str.toCharArray()){
                if (c == '0'){
                    zeroNum++;
                }else {
                    oneNum++;
                }
            }
            for (int i = m; i >= zeroNum;i--){
                for (int j = n; j >= oneNum;j--){
                    dp[i][j] = Math.max(dp[i][j],dp[i-zeroNum][j-oneNum]+1);
                }
            }
        }
        return dp[m][n];
    }

    public int findMaxForm2(String[] strs, int m, int n) {
        int length = strs.length;
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        for (int i = 1; i <= length; i++) {
            int[] zerosOnes = getZerosOnes(strs[i - 1]);
            int zeros = zerosOnes[0], ones = zerosOnes[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[length][m][n];
    }
    public int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }

}
