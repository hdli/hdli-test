package com.example.hdlitest.leetcode.huisu;

import java.util.*;

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

    /**
     * 利用全局变量used来记录元素是否被使用过
     * @param nums
     * @param startIndex
     */
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

    /**
     * 这种方式需要创建很多的集合，
     * @param nums
     * @param startIndex
     */
    private void subsetsWithDupHelper2(int[] nums, int startIndex){
        result.add(new ArrayList<>(path));

        Set<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++){
            //当前元素和前一个元素相同，并且前一个元素已经添加过了，则跳过
            if (i > 0 && nums[i] == nums[i - 1] && set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            path.add(nums[i]);
            subsetsWithDupHelper(nums, i + 1);
            path.removeLast();
        }
    }


}
