package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author luyi
 * @date 2024/1/16 00:00
 */
public class Ziji_Subsets_78 {

    LinkedList<Integer> temp = new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();

    /**
     * 子集：给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * 获取解析树上所有节点，组合与分割都是叶子节点取结果
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        test(nums,0);
        return result;
    }

    private void test(int[] nums,int startIndex){
        result.add(new ArrayList<>(temp));
        for(int i = startIndex;i<nums.length;i++){
            temp.add(nums[i]);
            test(nums,i+1);
            temp.removeLast();
        }
    }
}
