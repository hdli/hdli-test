package com.example.hdlitest.leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luyi
 * @date 2023/6/10 17:02
 */
public class FourSum_18 {


    public static void main(String[] args) {
        int [] nums = {1000000000,1000000000,1000000000,1000000000};
        int target = -294967296;
        List<List<Integer>> lists = fourSum(nums, target);
        System.out.println(lists);

    }

    /**
     * 三数之和 的基础上增加一层循环    先确定 a和b  c和d使用 双指针取值
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int k = 0;k<nums.length;k++){
            //第一个元数据剪枝
            if(nums[k] > 0 && target > 0 && nums[k] > target){
                continue;
            }
            if (k > 0 && nums[k] == nums[k-1]){
                continue;
            }
            for(int i = k+1;i<nums.length;i++){

                long temp = (long)nums[i]+nums[k];
                if(temp > 0 && target > 0 && temp > target){
                    continue;
                }
                if(i > k+1 && nums[i] == nums[i-1]){
                    continue;
                }
                int left = i+1;
                int right = nums.length -1;
                while(left < right){
                    //运算可能超出int的最大值，当int值超过最大限制（即2147483647）时，它将会变成最小的负数（即-2147483648）
                    long total = temp + nums[left] + nums[right];
                    if(total > target){
                        right--;
                    }else if(total < target){
                        left++;
                    }else{
                        result.add(Arrays.asList(nums[k],nums[i],nums[left],nums[right]));
                        while(left < right && nums[left] == nums[left+1]){
                            left++;
                        }
                        while(left < right && nums[right] == nums[right-1]){
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
