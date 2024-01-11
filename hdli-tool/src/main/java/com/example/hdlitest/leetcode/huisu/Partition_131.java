package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author luyi
 * @date 2023/5/13 9:42 PM
 */
public class Partition_131 {

    List<List<String>> result = new ArrayList<>();
    LinkedList<String> temp = new LinkedList<>();

    public static void main(String[] args) {
        String s = "aab";
        Partition_131 partition131 = new Partition_131();
        partition131.partition(s);
    }

    public List<List<String>> partition(String s) {
        recursion(s,0);
        return result;
    }

    /**
     *
     * @param s
     * @param startIndex 代表本次切割开始位置，i的移动决定了切割的end位置【startIndex,i】
     */
    private void recursion(String s,int startIndex){
        //如果起始位置大于s的大小，说明找到了一组分割方案
        if (startIndex >= s.length()){
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            //如果是回文子串，则记录
            if (isPalindromic(s,startIndex,i)){
                temp.addLast(s.substring(startIndex,i+1));
            }else {
                //efe ,当前截取的不是，不代表后序截取的不是
                continue;
            }
            //起始位置后移，保证不重复
            recursion(s,i+1);
            temp.removeLast();
        }
    }


    private boolean isPalindromic(String s,int start,int end){
        for (int i = start,j=end;i<j; i++,j--) {
            if (s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }



}
