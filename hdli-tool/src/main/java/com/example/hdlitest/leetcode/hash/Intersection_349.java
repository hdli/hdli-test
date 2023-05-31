package com.example.hdlitest.leetcode.hash;

import org.checkerframework.checker.units.qual.A;

import java.util.*;

/**
 * @author luyi
 * @date 2023/5/25 23:48
 */
public class Intersection_349 {

    /**
     * 两个数组的交集
     * @param args
     */
    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        int[] ints = intersection2(nums1, nums2);
        System.out.println(ints);
    }

    /**
     * 排序 + 双指针
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n1L = nums1.length;
        int n2L = nums2.length;
        //俩个数组的交集最多就是一个数据的长度
        int[] intersection = new int[Math.min(n1L,n2L)];
        int index = 0;
        for (int i = 0,j=0; i < n1L && j < n2L;){
            int n1 = nums1[i];
            int n2 = nums2[j];
            if (n1 > n2){
                j++;
            }else if (n1 < n2){
                i++;
            }else {
                //保障结果的唯一性
                if (index == 0 || n1 != intersection[index-1]){
                    intersection[index++] = n1;
                }
                i++;
                j++;
            }
        }

        return Arrays.copyOfRange(intersection,0,index);
    }

    /**
     * 两个集合
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> n1Set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            n1Set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            boolean b = n1Set.contains(nums2[i]);
            if (b){
                res.add(nums2[i]);
            }
        }
        Iterator<Integer> iterator = res.iterator();
        int [] result = new int[res.size()];
        int index = 0;
        while (iterator.hasNext()){
            result[index] = iterator.next();
            index++;
        }
        return result;
    }
}
