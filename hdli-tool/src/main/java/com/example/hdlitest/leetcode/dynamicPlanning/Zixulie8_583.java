package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/20 00:41
 */
public class Zixulie8_583 {

    /**
     * 两个字符串的删除操作:
     * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
     *
     * 每步 可以删除任意一个字符串中的一个字符。
     *
     *
     * 动态规划：先求出 word1 和 word2的最长公共子序列，俩个字段长度都减去最长公共子序列长度的和就是删除最小步长
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();
        int [][] dp = new int[char1.length+1][char2.length+1];
        for(int i =1;i<= char1.length;i++){
            for(int j =1;j<= char2.length;j++){
                if(char1[i-1] == char2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int length = dp[word1.length()][word2.length()];
        return word1.length()+word2.length()-2*length;
    }

    /**
     * dp数组中存储需要删除的字符个数
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();
        int [][] dp = new int[char1.length+1][char2.length+1];
        for (int i = 0;i<= char1.length;i++){
            dp[i][0] = i;
        }
        for (int j = 0 ;j<= char2.length;j++){
            dp[0][j] = j;
        }
        for (int i = 1; i <= char1.length;i++){
            for (int j = 1;j <= char2.length;j++){
                if (char1[i-1] == char2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j]+1,dp[i][j-1]+1),
                            dp[i-1][j-1]+2);
                }
            }
        }
        return dp[char1.length][char2.length];
    }


    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        Zixulie8_583 zixulie8_583 = new Zixulie8_583();
        int i = zixulie8_583.minDistance2(a, b);
    }



}
