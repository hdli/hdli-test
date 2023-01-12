package com.example.hdlitest.leetcode;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author luyi
 * @date 2023/1/12 2:52 下午
 */
public class PalindromicNumber {

    /**
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     *
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     *
     * 例如，121 是回文，而 123 不是。
     *
     * 示例 1：
     *
     * 输入：x = 121输出：true
     * 示例 2：
     *
     * 输入：x = -121输出：false解释：从左向右读 为 -121 。 从右向左读 为 121- 。因此它不是一个回文数。
     * 示例 3：
     *
     * 输入：x = 10输出：false解释：从右向左读 为 01 。因此它不是一个回文数。
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要确认的数字：");
        while (true){
            String s = sc.nextLine();
            if(StringUtils.equals(s,"exit")){
                break;
            }
            if (!StringUtils.isNumeric(s)){
                System.out.println("请输入数字!");
            }else {
                Boolean palindromic = isPalindromic(Integer.parseInt(s));
                if (palindromic){
                    System.out.println("是回文数");
                }else {
                    System.out.println("不是回文数");
                }
            }
        }
        sc.close();
        System.out.println("退出验证");
    }



    public static Boolean isPalindromic(int x){
        if (x < 0){
            return false;
        }
        String str = x + "";
        StringBuilder builder = new StringBuilder();
        for (int i = str.length()-1; i >= 0; i--) {
            builder.append(str.charAt(i));
        }
        return StringUtils.equals(builder.toString(),str);
    }


}
