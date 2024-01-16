package com.example.hdlitest.leetcode.huisu;

import java.util.*;

/**
 * @author luyi
 * @date 2024/1/17 00:33
 */
public class Ziji_FindSubsequences_491 {

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    /**
     * 非递减子序列
     *
     * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
     *
     * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        test(nums,0);

        return result;
    }


    private void test(int [] nums,int startIndex){
        if (path.size() >= 2){
            result.add(new ArrayList<>(path));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++){
            //同层判断是否有存在已经处理过的 或 下一个数，小于当前数
            if ((!path.isEmpty() && nums[i] < path.getLast()) || set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            path.add(nums[i]);
            test(nums,i+1);
            path.removeLast();
        }
    }
}
