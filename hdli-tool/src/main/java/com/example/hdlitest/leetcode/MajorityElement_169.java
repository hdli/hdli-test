package com.example.hdlitest.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luyi
 * @date 2023/4/26 5:17 PM
 */
public class MajorityElement_169 {


    /**
     * 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     *
     * * 核心是得到数量过半的哪个元素
     *
     * @param args
     */
    public static void main(String[] args) {
        int [] a = new int[]{3,2,3};
        int i = majorityElement(a);
        System.out.println(i);
    }

    /**
     * 首次
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int a = nums.length/2;
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for (int i = 0;i < nums.length;i++){
            Integer integer = map.get(nums[i]);
            if (integer == null){
                map.put(nums[i],1);
            }else {
                map.put(nums[i],++integer);
            }
        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            Integer value = entry.getValue();
            if (value > a){
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 减少循环
     * 哈希表
     * @param nums
     * @return
     */
    public static int m2(int[] nums){
        int a = nums.length/2;
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for (int i = 0;i < nums.length;i++){
            Integer integer = map.get(nums[i]);
            if (integer == null){
                integer = 1;
            }else {
                integer++;
            }
            if (integer > a){
                return nums[i];
            }
            map.put(nums[i],integer);
        }
        return 0;
    }

    /**
     * 线上最优
     * 多数
     * @param nums
     * @return
     */
    public static int m3(int[] nums){
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 摩尔投票法
     *
     * 核心就是对拼消耗
     *
     * Boyer-Moore投票算法
     * @param nums
     * @return
     */
    public static int m4(int[] nums){
        //诸王争霸赛开始【规则是目前投票数为0的话换候选人，自己人给自己人投票，敌方减票】
        //摩尔投票法为啥成立？因为这里的众数是指大于总数数目的二分之一，举两个个极端例子
        //121311【肯定有相邻的，其他的】或者111123【全部联合起来，敌方都抵消不了】
        int num = nums[0];//我先来做霸王
        int cnt = 1;//目前帮派就我一个人，遍历下去看看还有没有自己人为自己撑腰打气，首先遇到对手就被搞下去了
        for(int i = 1; i < nums.length; ++i){
            if(nums[i] == num){
                cnt++;//帮派的人来撑腰了，票数++
            } else{
                cnt--;//敌方来骚扰我当霸王，票数--
                if(cnt == 0){//没了，目前帮派人不够地方多，话语权没有
                    num = nums[i];//更换霸王
                    cnt = 1;//新的霸王重新计数
                }
            }
        }
        //选出来笑到最后的霸王
        return num;
    }
}
