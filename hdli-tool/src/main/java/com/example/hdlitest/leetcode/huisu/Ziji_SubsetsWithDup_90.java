package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author luyi
 * @date 2024/1/16 00:08
 */
public class Ziji_SubsetsWithDup_90 {

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    boolean[] used;

    /**
     * 子集2
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     * 这道题目和78.子集 (opens new window)区别就是集合里有重复元素了，而且求取的子集要去重
     *
     * 那么关于回溯算法中的去重问题，在40.组合总和II (opens new window)中已经详细讲解过了，和本题是一个套路。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0){
            result.add(path);
            return result;
        }
        Arrays.sort(nums);
        used = new boolean[nums.length];
        subsetsWithDupHelper(nums, 0);
        return result;
    }

    private void subsetsWithDupHelper(int[] nums, int startIndex){
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length){
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]){
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            subsetsWithDupHelper(nums, i + 1);
            path.removeLast();
            used[i] = false;
        }
    }


}
