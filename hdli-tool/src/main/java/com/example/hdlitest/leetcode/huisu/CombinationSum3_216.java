package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和 3
 * @author luyi
 * @date 2023/5/2 5:11 PM
 */
public class CombinationSum3_216 {

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> temp = new LinkedList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {


        t(n,k,1,0);

        return result;
    }


    private void t(int targetSum, int k, int startIndex,int curSum){
        //剪枝
        if (curSum > targetSum){
            return;
        }
        if (temp.size() == k){
            if (targetSum == curSum){
                result.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = startIndex; i <= 9; i++) {
            temp.add(i);
            curSum+=i;
            t(targetSum,k,i+1,curSum);
            curSum-=i;
            temp.removeLast();
        }
    }
}
