package com.example.hdlitest.leetcode.dynamicPlanning;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luyi
 * @date 2024/2/19 00:02
 */
public class Zixulie7_392 {

    /**
     * 判断子序列 简单题
     *
     * 双指针法
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if(s.length() > t.length()){
            return false;
        }
        int n = s.length();
        int m = t.length();
        int i = 0,j = 0;
        while (i < n && j < m){
            if (s.charAt(i) == t.charAt(j)){
                //匹配上，那就s和t都移动
                i++;
            }
            //没匹配上就t移动
            j++;
        }
        //判断i是否移动到了s的最后一位了
        return i == n;
    }

    /**
     * 1、确定dp数组（dp table）以及下标的含义
     * dp[i][j] 表示以下标i-1为结尾的字符串s，和以下标j-1为结尾的字符串t，相同子序列的长度为dp[i][j]。
     *
     * 注意这里是判断s是否为t的子序列。即t的长度是大于等于s的。
     *
     * 2、确定递推公式
     * 在确定递推公式的时候，首先要考虑如下两种操作，整理如下：
     *
     * if (s[i - 1] == t[j - 1])
     * t中找到了一个字符在s中也出现了
     * if (s[i - 1] != t[j - 1])
     * 相当于t要删除元素，继续匹配
     * if (s[i - 1] == t[j - 1])，那么dp[i][j] = dp[i - 1][j - 1] + 1;，因为找到了一个相同的字符，相同子序列长度自然要在dp[i-1][j-1]的基础上加1（如果不理解，在回看一下dp[i][j]的定义）
     *
     * if (s[i - 1] != t[j - 1])，此时相当于t要删除元素，t如果把当前元素t[j - 1]删除，那么dp[i][j] 的数值就是 看s[i - 1]与 t[j - 2]的比较结果了，即：dp[i][j] = dp[i][j - 1];
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequenceDp(String s, String t) {
        if(s.length() > t.length()){
            return false;
        }
        int [][] dp = new int[s.length()+1][t.length()+1];
        for (int i = 1; i <= s.length();i++){
            for (int j = 1; j <= t.length();j++){
                if (s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[s.length()][t.length()] == s.length();
    }
}
