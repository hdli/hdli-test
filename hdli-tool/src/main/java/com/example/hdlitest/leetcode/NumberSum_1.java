package com.example.hdlitest.leetcode;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author luyi
 * @date 2023/1/12 4:23 下午
 */
public class NumberSum_1 {

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * 你可以按任意顺序返回答案。
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        int [] nums = new int[]{1,2,3};

        int[] index = getIndexByHash(nums, 5);
        System.out.println(JSON.toJSONString(index));
    }

    /**
     * 暴力破解
     * @param nums
     * @param target
     * @return
     */
    public static int [] getIndex(int [] nums,int target){
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if ((nums[i]+nums[j]) == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public static int [] getIndexByHash(int [] nums,int target){
        //key：数据i的值  value:i
        Map<Integer,Integer> map = Maps.newHashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }else {
                map.put(nums[i],i);
            }
        }
        return new int[0];
    }
}
