package com.example.hdlitest.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luyi
 * @date 2023/6/9 20:55
 */
public class FourSumCount_454 {


    /**
     *  四数相加 II
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                Integer key = nums1[i]+nums2[j];
                map.put(key,map.getOrDefault(key,0)+1);
            }
        }
        Integer count = 0;
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                Integer key = nums3[i]+nums4[j];
                if (map.containsKey(0-key)){
                    count += map.get(0 - key);
                }
            }
        }
        return count;
    }
}

