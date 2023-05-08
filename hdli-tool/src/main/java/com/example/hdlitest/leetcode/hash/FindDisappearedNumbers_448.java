package com.example.hdlitest.leetcode.hash;

import com.google.common.primitives.Ints;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luyi
 * @date 2023/4/26 7:46 PM
 */
public class FindDisappearedNumbers_448 {

    /**
     * 找到数组中消失的数字
     * @param args
     */
    public static void main(String[] args) {
        int [] a = {4,3,2,7,8,2,3,1};
        List<Integer> disappearedNumbers = findDisappearedNumbers(a);
        System.out.println(disappearedNumbers);
    }

    /**
     * 网上优化方法
     * nums 中的数都是[1,n],所以把nums中的数作为新数组中的下标，并赋值1，那么不是1的下标就是缺失的值
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers3(int[] nums) {
        int [] a = new int[nums.length];
        for (int num : nums) {
            a[num-1] = 1;
        }
        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (a[i] < 1){
                result.add(i+1);
            }
        }
        return result;
    }


    /**
     * hash
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        if (map.size() == nums.length){
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = 1; i <= nums.length; i++) {
            if (!map.containsKey(i)){
                result.add(i);
            }
        }
        return result;
    }

    /**
     * hash
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> result = new ArrayList<>(nums.length);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)){
                result.add(i);
            }
        }
        return result;
    }


}
