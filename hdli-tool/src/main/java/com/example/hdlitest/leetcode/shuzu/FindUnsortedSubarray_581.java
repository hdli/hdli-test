package com.example.hdlitest.leetcode.shuzu;

import java.util.Arrays;

/**
 * @author luyi
 * @date 2023/5/6 10:51 PM
 */
public class FindUnsortedSubarray_581 {

    /**
     * 最短无序连续子数组：数组, 排序
     *
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        if (isSorted(nums)){
            return 0;
        }
        int [] numsSorted = new int[nums.length];
        System.arraycopy(nums,0,numsSorted,0,nums.length);
        Arrays.sort(numsSorted);
        int left = 0;
        while (nums[left] == numsSorted[left]){
            left++;
        }
        int right = nums.length - 1;
        while (nums[right] == numsSorted[right]){
            right--;
        }
        return right-left+1;
    }

    private boolean isSorted(int [] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums [i] > nums[i+1]){
                return false;
            }
        }
        return true;
    }
}
