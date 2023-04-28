package com.example.hdlitest.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author luyi
 * @date 2023/4/26 11:25 PM
 */
public class Search_704 {


    public static void main(String[] args) {
        LinkedList<String> a = new LinkedList<>();

    }

    /**
     * 数组二分法
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while(l <= r){
            int m = (l + r)/2;
            if(nums[m] > target){
                r = m-1;
            }else if (nums[m] < target){
                l = m +1;
            }else{
                return m;
            }
        }
        return -1;
    }
}
