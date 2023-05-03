package com.example.hdlitest.leetcode.shuzu;

import java.util.Arrays;

/**
 * 有序数组的平方
 * @author luyi
 * @date 2023/4/30 8:01 PM
 */
public class SortedSquares_977 {


    /**
     * 性能不够
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {

        int [] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {

            double pow = Math.pow(nums[i], 2);
            result[i] = (int) pow;

        }
        Arrays.sort(result);
        return result;
    }

    /**
     *
     * 「数组nums 已经按照升序排序」这个条件。显然，如果数组nums 中的所有数都是非负数那么将每个数平方后，数组仍然保持升序；
     * 如果数组nums中的所有数都是负数，那么将每个数平方后数组会保持降序。
     * 这样一来，如果我们能够找到数组nums中负数与非负数的分界线，那么就可以用类似「归并排序」的方法了。
     *
     * 同样地，我们可以使用两个指针分别指向位置0和n−1，每次比较两个指针对应的数，选择较大的那个逆序放入答案并移动指针。
     *
     *
     *
     * 如果数组中有正负数，那么平房后，负数的一边是降序，正数的一边是升序，从0和n-1两边开始对比，取最大的放到结果的最后位置
     * @param nums
     * @return
     */

    public int[] sortedSquares2(int[] nums) {

        int [] result = new int[nums.length];
        //i从左边开始，j从右边开始，pos操作指针（默认从右边开始）
        for (int i = 0,j = nums.length -1 ,pos = nums.length -1; i <= j;pos--) {
            int left = nums[i]*nums[i];
            int right = nums[j]*nums[j];
            if (left > right){
                result[pos] = left;
                i++;
            }else {
                result[pos] = right;
                j--;
            }
        }
        return result;
    }


}
