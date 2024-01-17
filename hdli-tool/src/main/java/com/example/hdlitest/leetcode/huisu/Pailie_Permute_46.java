package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author luyi
 * @date 2024/1/17 23:23
 */
public class Pailie_Permute_46 {


    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    /**
     * 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        recursion(nums,used);
        return result;
    }

    private void recursion(int[] nums,boolean[] used){
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0;i<nums.length;i++){
            //用过的就跳过
            if(used[i]){
                continue;
            }
            //通过判断path中是否存在数字，排除已经选择的数字
//            if (path.contains(nums[i])) {
//                continue;
//            }
            used[i] = true;
            path.add(nums[i]);
            recursion(nums,used);
            path.removeLast();
            used[i] = false;
        }
    }
}
