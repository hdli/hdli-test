package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/16 00:29
 */
public class Zixulie4_1143 {


    /**
     * 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     *
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     *
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     *
     *
     *
     * 本题和动态规划：718. 最长重复子数组 (opens new window)区别在于这里不要求是连续的了，
     * 但要有相对顺序，即："ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] char1 = text1.toCharArray();
        char[] char2 = text2.toCharArray();

        int [][] dp = new int[char1.length+1][char2.length+1];
        for (int i =1;i<= char1.length;i++){
            for (int j = 1;j<=char2.length;j++){
                if (char1[i-1] == char2[j-1]){
                    //连续
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    //不连续的情况
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
