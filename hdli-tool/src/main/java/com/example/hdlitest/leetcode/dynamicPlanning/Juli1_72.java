package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/21 00:11
 */
public class Juli1_72 {

    /**
     * 编辑距离:给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
     *
     * 你可以对一个单词进行如下三种操作：
     *
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();
        int [][] dp = new int[char1.length+1][char2.length+1];
        for (int i = 0;i<= char1.length;i++){
            dp[i][0] = i;
        }
        for (int j = 0 ;j<= char2.length;j++){
            dp[0][j] = j;
        }
        for (int i = 1; i <= char1.length;i++) {
            for (int j = 1; j <= char2.length; j++) {
                if (char1[i - 1] == char2[j - 1]){
                    //相同就不做处理，没有增加操作
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    //不等有3中，1、删除word1的 dp[i-1][j]+1 2、删除word2的 dp[i][j-1]+1 3、替换 dp[i-1][j-1]+1
                    dp[i][j] = Math.min(
                            Math.min(dp[i-1][j]+1,dp[i][j-1]+1),
                            dp[i-1][j-1]+1
                            );
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
