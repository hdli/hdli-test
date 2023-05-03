package com.example.hdlitest.leetcode.shuzu;

/**
 * @author luyi
 * @date 2023/5/3 4:04 PM
 */
public class SortColors_75 {

    public static void main(String[] args) {
        int [] nums = {1,2,0};
        sortColors(nums);
    }

    /**
     * 双指针
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int p0 = 0;
        int p2 = nums.length -1;
        for (int i = 0; i <= p2; i++) {
            while (i <= p2 && nums[i] == 2){
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                p2--;
            }
            //先判断 2，后判断0，如：1，2，0第二步是将2与0替换位置，如果先判断0，那么结果就变成1，0，2
            if (nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                p0++;
            }
        }
    }
}
