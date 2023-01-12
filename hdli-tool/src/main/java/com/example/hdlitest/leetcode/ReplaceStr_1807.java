package com.example.hdlitest.leetcode;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luyi
 * @date 2023/1/12 4:56 下午
 */
public class ReplaceStr_1807 {

    /**
     * 给你一个字符串 s ，它包含一些括号对，每个括号中包含一个 非空 的键。
     *
     * 比方说，字符串 "(name)is(age)yearsold" 中，有 两个 括号对，分别包含键 "name" 和 "age" 。
     * 你知道许多键对应的值，这些关系由二维字符串数组 knowledge 表示，其中 knowledge[i] = [keyi, valuei] ，表示键 keyi 对应的值为 valuei 。
     *
     * 你需要替换 所有 的括号对。当你替换一个括号对，且它包含的键为 keyi 时，你需要：
     *
     * 将 keyi 和括号用对应的值 valuei 替换。
     * 如果从 knowledge 中无法得知某个键对应的值，你需要将 keyi 和括号用问号 "?" 替换（不需要引号）。
     * knowledge 中每个键最多只会出现一次。s 中不会有嵌套的括号。
     *
     * 请你返回替换 所有 括号对后的结果字符串。
     *
     * @param args
     */
    public static void main(String[] args) {


        String a = "(name) asasa (age)";
        System.out.println("a".equals(String.valueOf(a.charAt(2))));

        a= a.replaceAll("(name)","kk");


        System.out.println(a);
    }

    /**
     * 失败
     * @param s
     * @param knowledge
     * @return
     */
    public static String evaluate(String s, List<List<String>> knowledge) {
        for (List<String> k : knowledge){
            String key = "("+k.get(0)+")";
            if (s.contains(key)){
                s = s.replaceAll(key, k.get(1));
            }
        }
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if ("(".equals(String.valueOf(s.charAt(i)))){
                leftList.add(i);
            }
            if (")".equals(String.valueOf(s.charAt(i)))){
                rightList.add(i);
            }
        }
        for (int i = 0; i < leftList.size(); i++) {
            Integer left = leftList.get(i);
            Integer right = rightList.get(i);
            String substring = s.substring(left, right);
            s = s.replace(substring, "?");
        }
        return s;
    }

    /**
     * 官方解法
     * @param s
     * @param knowledge
     * @return
     */
    public static String evaluate2(String s, List<List<String>> knowledge) {
        Map<String, String> dict = new HashMap<String, String>();
        for (List<String> kd : knowledge) {
            dict.put(kd.get(0), kd.get(1));
        }
        boolean addKey = false;
        StringBuilder key = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('(' == c){
                addKey = true;
            }else if (')' == c){
                if (dict.containsKey(key.toString())){
                    res.append(dict.get(key.toString()));
                }else {
                    res.append("?");
                }
                key = new StringBuilder();
                addKey = false;
            }else {
                if (addKey){
                    key.append(c);
                }else {
                    res.append(c);
                }
            }
        }
        return res.toString();
    }

}
