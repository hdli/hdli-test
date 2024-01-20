package com.example.hdlitest.leetcode.tanxin;

import java.util.Arrays;

/**
 * @author luyi
 * @date 2024/1/20 15:18
 */
public class Easy_LargestSumAfterKNegations_1005 {
    /**
     * K 次取反后最大化的数组和
     * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
     *
     * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
     * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
     *
     * 以这种方式修改数组后，返回数组 可能的最大和 。
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        //第一步 贪心，将数组中的所有负数变正
        Arrays.sort(nums);
        for (int i = 0 ;i<nums.length;i++){
            if (nums[i] >= 0){
                break;
            }
            if (k > 0){
                nums[i] = nums[i] * (-1);
                k--;
            }else {
                break;
            }
        }
        //第二步 贪心，如果还剩下k次，取数组中最小数转
        if (k > 0){
            Arrays.sort(nums);
            //剩余 奇数 数组中最小数 取反
            if (k%2 == 1){
                nums[0]=nums[0]*(-1);
            }
        }
        //最后取和
        return Arrays.stream(nums).sum();
    }
}
