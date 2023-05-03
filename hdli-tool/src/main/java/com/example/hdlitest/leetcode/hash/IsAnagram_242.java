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


    public static void main(String[] args) {
        String s = "aacc";
        String t = "ccac";
        boolean anagram = isAnagram(s, t);
        System.out.println(anagram);
    }


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
}
