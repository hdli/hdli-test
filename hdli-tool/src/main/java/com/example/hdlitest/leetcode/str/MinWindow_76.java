package com.example.hdlitest.leetcode.str;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author luyi
 * @date 2023/5/21 00:13
 */
public class MinWindow_76 {

    /**
     * 最小覆盖子串
     * @param args
     */
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        MinWindow_76 minWindow76 = new MinWindow_76();

        System.out.println(minWindow76.minWindow2(s, t));
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



    Map<Character,Integer> tMap = new HashMap<>();
    Map<Character,Integer> sMap = new HashMap<>();

    public String minWindow2(String s, String t) {
        String minResult = "";
        if (s.length() < t.length()){
            return minResult;
        }
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i),tMap.getOrDefault(t.charAt(i),0)+1);
        }
        int finalStart = 0,finalEnd = s.length();
        int minLength = s.length();

        boolean flag = false;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            sMap.put(s.charAt(end),sMap.getOrDefault(s.charAt(end),0)+1);
            while (check()){
                flag = true;
                int curLength = end-start+1;
                minLength = Math.min(curLength,minLength);
                if (minLength == curLength){
                    finalStart = start;
                    finalEnd = end;
                }
                sMap.put(s.charAt(start),sMap.getOrDefault(s.charAt(start),0)-1);
                start++;
            }
        }
        if (!flag){
            return minResult;
        }
        return s.substring(finalStart,finalEnd+1);
    }

    private Boolean check(){
        if (sMap.size() < tMap.size()){
            return false;
        }
        Iterator<Map.Entry<Character, Integer>> iterator = tMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Character, Integer> next = iterator.next();
            Character key = next.getKey();
            Integer value = next.getValue();
            if (sMap.getOrDefault(key,0) < value){
                return false;
            }
        }
        return true;
    }




}
