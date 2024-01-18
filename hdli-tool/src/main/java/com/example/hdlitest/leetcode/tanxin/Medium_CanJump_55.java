package com.example.hdlitest.leetcode.tanxin;

/**
 * @author luyi
 * @date 2024/1/19 00:16
 */
public class Medium_CanJump_55 {


    public static void main(String[] args) {
        int[] nums ={3,2,1,0,4};
        canJump(nums);

    }

    /**
     * 跳跃游戏:给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
     *
     * ----------判断覆盖范围----------
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        if(nums.length == 1){
            return true;
        }
        int range = 0;
        for(int i = 0;i<= range;i++){
            range = Math.max(nums[i]+i,range);
            if(range >= nums.length -1){
                return true;
            }
        }
        return false;
    }
}
