package com.example.hdlitest.leetcode.stack;

import java.util.Stack;

/**
 * @author luyi
 * @date 2023/7/2 14:30
 */
public class RemoveDuplicates_easy_1047 {

    /**
     * 删除字符串中的所有相邻重复项
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i < s.length();i++){
            if(stack.isEmpty()){
                stack.push(s.charAt(i));
            }else{
                if(stack.peek().equals(s.charAt(i))){
                    stack.pop();
                }else{
                    stack.push(s.charAt(i));
                }
            }
        }
        return stackToString(stack);
    }



    public String stackToString(Stack<Character> stack){
        String str = "";
        while (!stack.isEmpty()){
            str = stack.pop() + str;
        }
        return str;
    }
}
