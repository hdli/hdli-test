package com.example.hdlitest.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luyi
 * @date 2023/4/26 10:49 AM
 */
public class SingleNumber_136 {

    /**
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     *
     * @param args
     */
    public static void main(String[] args) {
        int [] a = new int[]{2,2,1};
        int i = singleNumber(a);
        System.out.println(i);
    }

    /**
     * 不需要额外空间的方法，就往位运算上想
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        return Arrays.stream(nums).reduce((a, b)->a^b).getAsInt();
    }

    public static int singleNumber2(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>(nums.length/2 + 1);
        for (int i = 0; i < nums.length; i++) {
            Integer integer = map.get(nums[i]);
            if (integer == null){
                map.put(nums[i],1);
            }else {
                map.remove(nums[i]);
            }
        }
        return (int) map.keySet().toArray()[0];

    }
}
