package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/21 22:58
 */
public class Juli3_516 {
    /**
     * 最长回文子序列:
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     *
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     *
     *
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int [][] dp = new int[s.length()+1][s.length()+1];
        for (int i = 0 ; i< s.length();i++){
            //i=j时 单个字母 一定是一个1位长度的回文串
            dp[i][i] = 1;
        }
        for (int i = s.length() -1; i >= 0;i--){
            for (int j = i+1;j<s.length();j++){
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1]+2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }
}
