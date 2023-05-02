package com.example.hdlitest.leetcode.str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author luyi
 * @date 2023/4/30 9:51 PM
 */
public class LengthOfLongestSubstring_3 {

    public static void main(String[] args) {
        String a ="c";
        int i = lengthOfLongestSubstring(a);
        System.out.println(i);
    }

    /**
     * 有问题，只能用滑动
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length()==0){
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        //最长子串长度
        int max = 0;
        //滑动窗口左下标，i相当于滑动窗口右下标
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                //map.get():返回字符所对应的索引，当发现重复元素时，窗口左指针右移
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            //map.get('a')=0,因为map中只有第一个a的下标，然后更新left指针到原来left的的下一位

            //再更新map中a映射的下标
            map.put(s.charAt(i),i);
            //比较两个参数的大小
            max = Math.max(max,i-left+1);
        }
        return max;
    }

    public static int lengthOfLongestSubstring2(String s) {
        if (s.length()==0){
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        //最长子串长度
        int max = 0;
        //滑动窗口左下标，i相当于滑动窗口右下标
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                //map.get():返回字符所对应的索引，当发现重复元素时，窗口左指针右移
                //需要abba，取left最大
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            //map.get('a')=0,因为map中只有第一个a的下标，然后更新left指针到原来left的的下一位

            //再更新map中a映射的下标
            map.put(s.charAt(i),i);
            //比较两个参数的大小
            max = Math.max(max,i-left+1);
        }
        return max;
    }
}
