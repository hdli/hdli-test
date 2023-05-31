package com.example.hdlitest.leetcode.shuzu;

/**
 * @author luyi
 * @date 2023/5/28 14:26
 */
public class SearchRange_34 {

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     *
     * 1、先利用数组二分发找到target的一个下标
     * 2、向左和target一样的下标-1，向右和target一样的下标+1
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int [] result = new int[]{-1,-1};
        if (nums.length == 0){
            return result;
        }
        if (nums[0] > target || nums[nums.length -1] < target){
            return result;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r){
            int m = (l+r)/2;
            if (nums[m] > target){
                r = m-1;
            }else if (nums[m] < target){
                l = m+1;
            }else {
                int index0 = m;
                int index1 = m;
                while (index0-1 >= 0 && nums[index0-1] == target){
                    index0--;
                }
                while (index1+1 <= nums.length-1 && nums[index1+1] == target){
                    index1++;
                }
                result[0] = index0;
                result[1] = index1;
                return result;
            }
        }
        return result;
    }


}
