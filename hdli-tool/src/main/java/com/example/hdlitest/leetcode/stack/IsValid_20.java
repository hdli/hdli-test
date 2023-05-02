package com.example.hdlitest.leetcode.stack;

import java.util.Stack;

/**
 * @author luyi
 * @date 2023/4/14 4:51 PM
 */
public class IsValid_20 {


    public static void main(String[] args) {

    }


    /**
     * 轻松掌握栈的基本操作
     *      * 栈的所有操作都是基于栈顶进行的，这是它的特色
     *      * https://blog.csdn.net/m0_52517879/article/details/123795249
     * @param s
     * @return
     */
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
