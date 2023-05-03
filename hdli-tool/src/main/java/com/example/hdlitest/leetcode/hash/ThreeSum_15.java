package com.example.hdlitest.leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * @author luyi
 * @date 2023/5/2 9:57 PM
 */
public class ThreeSum_15 {

    /**
     * 双指针，
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0){
                return result;
            }
            //a去重  相邻的数据相同跳过
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int left = i+1;
            int right = nums.length-1;
            while (right > left){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0){
                    right--;
                }else if (sum < 0){
                    left++;
                }else {
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    // 去重逻辑应该放在找到一个三元组之后，对b 和 c去重,
                    while (right > left && nums[right] == nums[right - 1]){
                        right--;
                    }
                    while (right > left && nums[left] == nums[left + 1]){
                        left++;
                    }
                    right--;
                    left++;
                }

            }
        }
        return result;
    }




}
