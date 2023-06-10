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
     * a+b+c=0
     * 先排序，因为是求值的内容所以可以先排序
     * 先固定第一个数 ，然后 后面俩数可以使用双指针法获取，改题关键是去重
     *
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
            //a去重  相邻的数据相同跳过   因为是已经排过序了
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
                    //                                    a  b      c   如果一上来就对b和c去重 那一个结果都得不到
                    //先获取一次结果，再处理b和c的去重问题 ，如：0，0，0，0，0
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
