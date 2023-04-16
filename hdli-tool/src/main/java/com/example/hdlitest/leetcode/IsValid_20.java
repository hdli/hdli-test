package com.example.hdlitest.leetcode;

import java.util.Stack;

/**
 * @author luyi
 * @date 2023/4/14 4:51 PM
 */
public class IsValid_20 {


    public static void main(String[] args) {

    }



    public static boolean isValid(String s){
        if (s.length() % 2 != 0){
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        for(char c: s.toCharArray()){
            if(c=='(')stack.push(')');
            else if(c=='[')stack.push(']');
            else if(c=='{')stack.push('}');
            else if(stack.isEmpty()||c!=stack.pop())return false;
        }
        return stack.isEmpty();
    }
}
