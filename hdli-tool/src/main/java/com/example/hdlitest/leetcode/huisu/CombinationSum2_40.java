package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luyi
 * @date 2023/5/12 10:25 PM
 */
public class CombinationSum2_40 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int [] userd = new int[candidates.length];
        // 为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        recursion(candidates,target,0,userd);
        return result;
    }

    private void recursion(int [] candidates,int target,int startIndex,int [] userd){
        if (target < 0){
            return;
        }
        if (target == 0){
            result.add(new ArrayList<>(temp));
        }
        for (int i = startIndex; i < candidates.length; i++) {
            //出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
            //使用标记数组 candidates[i] == candidates[i-1] 但是 这个可能是数组的两个相同的值，如果userd[i-1]==0 代表上一个元数据没有用
            if (i > 0 && candidates[i] == candidates[i-1] && userd[i-1]==0){
                continue;
            }
            temp.add(candidates[i]);
            target-=candidates[i];
            userd[i]=1;
            recursion(candidates,target,i+1,userd);
            userd[i]=0;
            target+=candidates[i];
            temp.remove(temp.size()-1);
        }
    }


}
