package com.example.hdlitest.leetcode.str;

/**
 * @author luyi
 * @date 2023/6/10 18:30
 */
public class ReverseStr_541 {


    /**
     * 反转字符串 II
     *  题目的意思其实概括为 每隔2k个反转前k个，尾数不够k个时候全部反转
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] charArray = s.toCharArray();
        for (int i = 0 ;i < charArray.length ; i+=2*k){
            int start = i;
            int end = i+k;
            //还没到边界
            if (end <= charArray.length){
                reverse(charArray,start,end-1);
            }else {
                //到边界了 ，剩余字符串不足k的情况
                reverse(charArray,start,charArray.length-1);
            }
        }
        return new String(charArray);
    }

    /**
     * 反转字符串
     */
    private void reverse(char[] charArray,int start,int end){
        while (start < end){
            char temp = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = temp;
            start++;
            end--;
        }
    }
}
