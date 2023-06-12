package com.example.hdlitest.leetcode.str;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * 反转字符串中的单词
 * @author luyi
 * @date 2023/6/10 20:27
 */
public class ReverseWords_151 {


    /**
     * 反转字符串中的单词
     * 1、使用Java语言的特性
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s=s.trim();
        String[] split = s.split("\\s+");
        List<String> list = Arrays.asList(split);
        Collections.reverse(list);

        return String.join(" ",list);
    }


    /**
     * 自己写函数处理
     */
    public String reverseWords2(String s) {
        //第一步：去掉多余的空格
        StringBuilder sb = removeSpace(s);
        //第二步：反转整个字符串
        reverseString(sb, 0, sb.length() - 1);
        //第三步：再反转每个单词
        reverseEachWord(sb);
        return sb.toString();
    }


    public StringBuilder removeSpace(String s) {
        int start = 0;
        int end = s.length() - 1;
        //去除头部空格
        while (s.charAt(start) == ' ') {
            start++;
        };
        //去除尾部空格
        while (s.charAt(end) == ' '){
            end--;
        }
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' '){
                sb.append(c);
            }else if (sb.charAt(sb.length()-1) != ' '){ // c 为空格 但是sb每个单词中间至少还需要一个空格
                sb.append(c);
            }
            //其他的空格都排除了
            start++;
        }
        return sb;
    }

    /**
     * 反转字符串指定区间[start, end]的字符
     */
    public void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    /**
     *  反转 其中的单词
     * @param sb
     */
    private void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = 1;
        int n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }


}
