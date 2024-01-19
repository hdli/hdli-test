package com.example.hdlitest.leetcode.tanxin;

import java.util.Arrays;

/**
 * @author luyi
 * @date 2024/1/19 23:36
 */
public class Medium_MaxSubArray_53 {

    /**
     * 最大子序和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 子数组 是数组中的一个连续部分。
     *
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int result = Integer.MIN_VALUE;
        int count = 0;
        for(int i = 0;i<nums.length;i++){
            count+=nums[i];
            result = Math.max(result, count);
            //计算起点的时候，一定是从 大于0的地方 开始计算，因为负数只会拉低总和，这就是贪心贪的地方
            //当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
            if(count < 0){
                //相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
                count = 0;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int [] as = {-2,1,-3,4,-1,2,1,-5,4};
        maxSubArray2(as);
    }

    /**
     * 还需要获取子数组
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count = 0;

        int end = 0;
        for(int i = 0;i<nums.length;i++){
            count+=nums[i];
            if(count > result){
                result = count;
                end = i;
            }
            //计算起点的时候，一定是从 大于0的地方 开始计算，因为负数只会拉低总和，这就是贪心贪的地方
            //当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
            if(count < 0){
                //相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
                count = 0;
            }
        }
        int a = result;
        int start = 0;
        for (int i = end; i >= 0;i--){
            a-=nums[i];
            if (a == 0){
                start = i;
            }
        }
        int[] ints = Arrays.copyOfRange(nums, start, end+1);
        return result;
    }
}
