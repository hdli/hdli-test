package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyi
 * @date 2023/5/13 11:13 PM
 */
public class RestoreIpAddresses_93 {

    List<String> result = new ArrayList<>();


    public static void main(String[] args) {
        String s = "255255";
        s = s.substring(0, 2 + 1) + "." + s.substring(2 + 1);
        System.out.println(s);
        s = s.substring(0, 2 + 1) + s.substring(2 + 2);
        System.out.println(s);

    }

    public List<String> restoreIpAddresses(String s) {
        recursion(s,0,0);
        return result;
    }

    private void recursion(String s,int startIndex,int pointSum){
        if (pointSum == 3){
            //还需要判断下最后一个点之后的数是否合法
            if (checkCode(s,startIndex,s.length()-1)){
                result.add(new String(s));
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (checkCode(s,startIndex,i)){
                //在str的后⾯插⼊⼀个逗点
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                pointSum++;
                recursion(s,i+2,pointSum);
                pointSum--;
                // 回溯删掉逗点
                s = s.substring(0, i + 1) + s.substring(i + 2);

            }else {
                break;
            }

        }
    }


    private boolean checkCode(String s,int start,int end){

        if (start > end) {
            return false;
        }
        // 0开头的数字不合法
        if (s.charAt(start) == '0' && start != end) {
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            // 遇到⾮数字字符不合法
            if (s.charAt(i) > '9' || s.charAt(i) < '0') {
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            // 如果⼤于255了不合法
            if (num > 255) {
                return false;
            }
        }
        return true;
    }


}
