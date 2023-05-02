package com.example.hdlitest.leetcode.shuzu;

/**
 * @author luyi
 * @date 2023/4/30 7:50 PM
 */
public class RemoveElement_27 {

    /**
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
