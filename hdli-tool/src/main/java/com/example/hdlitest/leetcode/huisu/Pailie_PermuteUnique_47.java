package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author luyi
 * @date 2024/1/18 00:00
 */
public class Pailie_PermuteUnique_47 {

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    /**
     * 全排列 存在重复数
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        recursion(nums,used);
        return result;
    }

    private void recursion(int[] nums,boolean[] used){
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0;i<nums.length;i++){
            if(i > 0 && nums[i] == nums[i-1] && !used[i-1]){
                continue;
            }
            if(used[i]){
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            recursion(nums,used);
            path.removeLast();
            used[i] = false;
        }
    }
}
