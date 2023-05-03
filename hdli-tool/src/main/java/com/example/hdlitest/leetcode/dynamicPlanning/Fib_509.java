package com.example.hdlitest.leetcode.dynamicPlanning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luyi
 * @date 2023/5/3 4:53 PM
 */
public class Fib_509 {

    /**
     * 动态规划：1、初始化dp数组，2、初始化dp数组，3、确定递推公式：F(n) = F(n - 1) + F(n - 2)，
     * 4、遍历顺序，5、打印dp数组
     * 斐波那契数
     * @param n
     * @return
     */
    public int fib(int n) {
        List<Integer> dp = new ArrayList<>(n);
        dp.add(0);
        dp.add(1);
        for (int i = 2; i <= n; i++) {
            //递推公式：F(n) = F(n - 1) + F(n - 2)
            int fn = dp.get(i-1)+dp.get(i-2);
            dp.add(fn);
        }
        return dp.get(n);
    }

    public static void main(String[] args) {
        int i = fib2(2);
        System.out.println(i);
    }

    /**
     * 递归
     * @param n
     * @return
     */
    public static int fib2(int n) {
        Integer a = 0;
        Integer b = 1;
        Integer c = 0;
        if (n == 0){
            return a;
        }
        if (n ==1){
            return b;
        }
        return t(a,b,c,n,2);
    }

    private static Integer t(Integer a,Integer b,Integer c,Integer n,Integer index){
        if (index > n){
            return c;
        }
        c= a+b;
        a = b;
        b = c;
        index++;
        return t(a,b,c,n,index);
    }





}
