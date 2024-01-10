package com.example.hdlitest.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 有效的字母异位词
 * @author luyi
 * @date 2023/5/2 2:46 PM
 */
public class IsAnagram_242 {

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     * @param args
     */
    public static void main(String[] args) {
        String s = "aacc";
        String t = "ccac";
        boolean anagram = isAnagram(s, t);
        System.out.println(anagram);
    }

    /**
     * 使用Map集合
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        Map<Character,Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) < 0){
                return false;
            }
        }

        return true;
    }

    /**
     * 使用数组
     * 因为s和t都是小写字母，
     * 需要把字符映射到数组也就是哈希表的索引下标上，因为字符a到字符z的ASCII是26个连续的数值，所以字符a映射为下标0，相应的字符z映射为下标25。
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        int [] re = new int[26];
        for(int i = 0;i<s.length();i++){
            re[s.charAt(i)-'a']++;
        }
        for(int i=0;i<t.length();i++){
            if(re[t.charAt(i)-'a']-- < 0){
                return false;
            }
        }
        for(int i = 0;i<26;i++){
            if(re[i] != 0){
                return false;
            }
        }
        return true;
    }
}
