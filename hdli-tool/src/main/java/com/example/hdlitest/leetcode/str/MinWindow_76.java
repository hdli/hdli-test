package com.example.hdlitest.leetcode.str;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luyi
 * @date 2023/5/21 00:13
 */
public class MinWindow_76 {


    public static void main(String[] args) {
        String s = "aa";
        String t = "aa";
        System.out.println(minWindow(s, t));
    }


    /**
     * 超出时间限制
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        String minResult = "";
        if (s.length() < t.length()){
            return minResult;
        } else {
            Map<Character,Integer> tMap = new HashMap<>(t.length());
            for (int i = 0; i < t.length(); i++) {
                tMap.put(t.charAt(i),tMap.getOrDefault(t.charAt(i),0)+1);
            }
            int start = 0;
            for (int end = t.length(); end <= s.length(); end++) {
                while (checkT(s.substring(start, end), tMap)) {
                    String substring = s.substring(start, end);
                    if (minResult.equals("")) {
                        minResult = substring;
                    } else {
                        minResult = minResult.length() > substring.length() ? substring : minResult;
                    }
                    //滑动开始位置
                    if (start+1 < s.length() && checkT(s.substring(start+1, end), tMap)){
                        start++;
                    }else {
                        break;
                    }
                }
            }
        }
        return minResult;
    }

    private static Boolean checkT(String substring,Map<Character,Integer> tMap){
        Map<Character,Integer> handT = new HashMap<>(tMap);
        for (int i = 0; i < substring.length(); i++) {
            char c = substring.charAt(i);
            if (handT.containsKey(c)){
                handT.put(c,handT.getOrDefault(c,0)-1);
                if (handT.get(c) < 0){
                    return false;
                } else if (handT.get(c) == 0){
                    handT.remove(c);
                }
            }
        }
        return handT.isEmpty();
    }


}
