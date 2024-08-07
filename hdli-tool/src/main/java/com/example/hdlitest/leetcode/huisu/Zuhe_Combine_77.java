package com.example.hdlitest.leetcode.huisu;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合
 * @author luyi
 * @date 2023/5/2 3:57 PM
 */
public class Zuhe_Combine_77 {






    static List<List<Integer>> result = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();


    public static void recall(int n,int k,int startIndex){
        if (k == 0){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex;i <= n;i++){
            path.add(i);
            recall(n,k-1,i+1);
            path.removeLast();
        }
    }



    /**
     *
     * 回溯
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * https://leetcode.cn/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
     * @param args
     */
    public static void main(String[] args) {
        int n = 4;
        int k = 2;

        recall(n,k,1);
        System.out.println(result);
    }


    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        t(n,k,1,result,temp);

        return result;
    }


    private static void t(int n, int k, int startIndex, List<List<Integer>> result, List<Integer> temp){
        if(temp.size() == k){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i = startIndex ; i<= n;i++){
            temp.add(i);
            t(n,k,i+1,result,temp);
            //回溯
            temp.remove(temp.size()-1);
        }
    }

    /**
     * 优化 剪枝
     * @param n
     * @param k
     * @param startIndex
     * @param result
     * @param temp
     */
    private static void t2(int n, int k, int startIndex, List<List<Integer>> result, List<Integer> temp){
        if(temp.size() == k){
            result.add(new ArrayList<>(temp));
            return;
        }
        //剪枝操作
        /**
         * 1、已经选择的元素个数：temp.size();
         * 2、所需需要的元素个数为: k - temp.size();
         * 3、列表中剩余元素（n-i） >= 所需需要的元素个数（k - temp.size()）
         * 4、在集合n中至多要从该起始位置 : i <= n - (k - path.size()) + 1，开始遍历
         * 为什么有个+1呢，因为包括起始位置，我们要是一个左闭的集合。
         */
        for(int i = startIndex ; i<= n-(k-temp.size() + 1);i++){
            temp.add(i);
            t2(n,k,i+1,result,temp);
            //回溯
            temp.remove(temp.size()-1);
        }
    }
}
