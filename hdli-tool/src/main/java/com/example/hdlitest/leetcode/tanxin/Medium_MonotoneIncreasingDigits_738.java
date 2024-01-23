package com.example.hdlitest.leetcode.tanxin;


/**
 * @author luyi
 * @date 2024/1/23 00:12
 */
public class Medium_MonotoneIncreasingDigits_738 {

    /**
     * 单调递增的数字:
     * 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。
     *
     * 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        char[] arr = s.toCharArray();
        //start默认赋数组长度，因为 1234可能数字本身就是自增的，不需要改变
        int flag = arr.length;
        for(int i = arr.length - 1; i > 0; i--){
            //一旦出现arr[i - 1] > arr[i]的情况（非单调递增），首先想让arr[i - 1]--，然后arr[i]给为9
            if(arr[i - 1] > arr[i]){
                arr[i - 1]--;
                arr[i] = '9';
                //记录开始变9 的位置 为什么不直接给strings[i]赋9，因为 1000
                flag = i;
            }
        }
        for(int i = flag; i < arr.length; i++){
            arr[i] = '9';
        }
        return Integer.parseInt(String.valueOf(arr));

    }
}
