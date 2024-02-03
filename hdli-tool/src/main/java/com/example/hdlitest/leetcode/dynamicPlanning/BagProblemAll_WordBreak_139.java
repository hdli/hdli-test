package com.example.hdlitest.leetcode.dynamicPlanning;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author luyi
 * @date 2024/2/3 17:59
 */
public class BagProblemAll_WordBreak_139 {
    /**
     * 单词拆分:给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
     *
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     *
     *
     * 动规五部曲分析如下：
     *
     * 1、确定dp数组以及下标的含义
     * dp[i] : 字符串长度为i的话，dp[i]为true，表示可以拆分为一个或多个在字典中出现的单词。
     *
     * 2、确定递推公式
     * 如果确定dp[j] 是true，且 [j, i] 这个区间的子串出现在字典里，那么dp[i]一定是true。（j < i ）。
     *
     * 所以递推公式是 if([j, i] 这个区间的子串出现在字典里 && dp[j]是true) 那么 dp[i] = true。
     *
     * 3、dp数组如何初始化
     * 从递推公式中可以看出，dp[i] 的状态依靠 dp[j]是否为true，那么dp[0]就是递推的根基，dp[0]一定要为true，否则递推下去后面都都是false了。
     *
     * 那么dp[0]有没有意义呢？
     *
     * dp[0]表示如果字符串为空的话，说明出现在字典里。
     *
     * 但题目中说了“给定一个非空字符串 s” 所以测试数据中不会出现i为0的情况，那么dp[0]初始为true完全就是为了推导公式。
     *
     * 下标非0的dp[i]初始化为false，只要没有被覆盖说明都是不可拆分为一个或多个在字典中出现的单词。
     *
     * 4、确定遍历顺序
     * 题目中说是拆分为一个或多个在字典中出现的单词，所以这是完全背包。
     *
     * 还要讨论两层for循环的前后顺序。
     *
     * 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
     *
     * 如果求排列数就是外层for遍历背包，内层for循环遍历物品。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        //dp[i] : 字符串长度为i的话，dp[i]为true，表示可以拆分为一个或多个在字典中出现的单词。
        boolean [] dp = new boolean[s.length()+1];
        //从递推公式中可以看出，dp[i] 的状态依靠 dp[j]是否为true，那么dp[0]就是递推的根基，dp[0]一定要为true，否则递推下去后面都都是false了。
        dp[0] = true;
        for (int i = 1; i <= s.length();i++){
            for (int j = 0; j < i; j++) {
                //如果确定dp[j] 是true，且 [j, i] 这个区间的子串出现在字典里，那么dp[i]一定是true。（j < i ）。
                //所以递推公式是 if([j, i] 这个区间的子串出现在字典里 && dp[j]是true) 那么 dp[i] = true。
                if (wordDict.contains(s.substring(j, i)) && dp[j]){
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }


    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                int len = word.length();
                if (i >= len && dp[i - len] && word.equals(s.substring(i - len, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }



    /**
     * 暴力搜索：回溯 超时
     * 遍历数组
     * @param s
     * @param wordDict
     * @param currentWord
     * @return
     */
    private boolean recall(String s, List<String> wordDict,String currentWord){
        if (currentWord.length() == s.length()){
            if (currentWord.equals(s)){
                return true;
            }
            return false;
        }
        for (String key : wordDict) {
            if (key.length() > s.length()){
                continue;
            }
            if ((currentWord.length() + key.length()) > s.length()){
                continue;
            }
            boolean recall = recall(s, wordDict, currentWord + key);
            if (recall){
                return true;
            }
        }
        return false;
    }

    /**
     * 暴力搜索：回溯法 截取字符串
     * 遍历字符串
     *
     * @param s
     * @param wordDict
     * @param startIndex
     * @return
     */
    private boolean recall2(String s, Set<String> wordDict,int startIndex){
        if (startIndex == s.length()) {
            return true;
        }
        for (int i = startIndex; i < s.length();i++) {
            String substring = s.substring(startIndex, i);
            if (!wordDict.contains(substring)){
                continue;
            }
            boolean b = recall2(s, wordDict, i + 1);
            if (b){
                return true;
            }
        }
        return false;
    }





    public static void main(String[] args) {
        String a = "bccdbacdbdacddabbaaaadababadad";
        ArrayList<String> strings = Lists.newArrayList("cbc","bcda","adb","ddca","bad","bbb","dad","dac","ba","aa","bd","abab","bb","dbda","cb","caccc","d","dd","aadb","cc","b","bcc","bcd","cd","cbca","bbd","ddd","dabb","ab","acd","a","bbcc","cdcbd","cada","dbca","ac","abacd","cba","cdb","dbac","aada","cdcda","cdc","dbc","dbcb","bdb","ddbdd","cadaa","ddbc","babb");
        BagProblemAll_WordBreak_139 wordBreak139 = new BagProblemAll_WordBreak_139();
        boolean recall = wordBreak139.recall(a, strings, "");
        System.out.println(recall);
    }
}
