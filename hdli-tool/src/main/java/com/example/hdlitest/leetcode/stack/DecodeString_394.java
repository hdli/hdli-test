package com.example.hdlitest.leetcode.stack;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 字符串解码
 * @author luyi
 * @date 2023/5/2 7:26 PM
 */
public class DecodeString_394 {

    public static void main(String[] args) {
        String a = "10[a]";
        String s = decodeString(a);
        System.out.println(s);
    }


    public static String decodeString(String s) {
        if (s == null || s.equals("")){
            return null;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) != ']'){
                stack.push(s.charAt(i)+"");
            }else {
                boolean flag = true;
                int x = 0;
                List<String> sub = new ArrayList<>();
                while (flag){
                    String pop = stack.pop();
                    if ("[".equals(pop)){
                        flag = false;
                        List<String> xList = new ArrayList<>();
                        xList.add(stack.pop());
                        while (!stack.isEmpty() && StringUtils.isNumeric(stack.peek())){
                            xList.add(stack.pop());
                        }
                        x = getX(xList);
                    }else {
                        sub.add(pop);
                    }
                }
                Collections.reverse(sub);
                String str = getStr(x, sub);
                stack.push(str);
            }
        }
        StringBuilder result = new StringBuilder();
        Collections.reverse(stack);
        while (!stack.isEmpty()){
            result.append(stack.pop());
        }

        return result.toString();
    }

    private static Integer getX(List<String> xList){
        Collections.reverse(xList);
        StringBuilder stringBuilder = new StringBuilder();
        for (String x:xList){
            stringBuilder.append(x);
        }
        return Integer.parseInt(stringBuilder.toString());
    }

    private static String getStr(int x,List<String> sub){
        StringBuilder stringBuilder = new StringBuilder();
        for (String a:sub){
            stringBuilder.append(a);
        }
        if (x == 0){
            return "";
        }
        if (x == 1){
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        while (x-- > 0){
            stringBuilder2.append(stringBuilder);
        }
        return stringBuilder2.toString();
    }

}
