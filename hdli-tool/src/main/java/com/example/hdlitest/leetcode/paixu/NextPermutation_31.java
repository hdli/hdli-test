package com.example.hdlitest.leetcode.paixu;

import java.util.Arrays;

/**
 * @author luyi
 * @date 2023/5/22 22:18
 */
public class NextPermutation_31 {

    /**
     * 下一个排列    大，大一点
     * @param args
     */
    public static void main(String[] args) {
        int [] a = {4,5,2,6,3,1};
        nextPermutation(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
    }

    /**
     * 4,5,2,6,3,1
     * i = nums.length-1
     * 1、倒着找到第一个 a[i-1] < a[i]                                      i= 3   a[i-1]= 2
     * 2、[i,end]是降序，找到最[i,end] 从后向前 查找第一个满足 a[i-1] < a[j]    a[i-1] = 2  j=4  a[4]= 3
     * 3、交换 j与 i-1的位置 得到 4,5,3,6,2,1
     * 4、[i,end]是降序，逆转改为升序  得到 4,5,3,1,2,6
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        for (int i = nums.length-1; i >= 1; i--) {
            //1、倒这找到第一个 升序的 a[i] > a[i+1]
            if (nums[i-1] < nums[i]){
                //2、[i,end]是降序，找到最[i,end) 从后向前 查找第一个满足 A[i-1] < A[j]
                for (int j = nums.length-1; j >= i; j--) {
                    if (nums[j] > nums[i-1]){
                        //3、交换 j与 i-1的位置
                        int temp = nums[j];
                        nums[j] = nums[i-1];
                        nums[i-1] = temp;
                        //4、[i,end]是降序，逆转改为升序
                        for (int k = i, m=nums.length-1;k < m; k++,m--) {
                            int temp1 = nums[k];
                            nums[k] = nums[m];
                            nums[m] = temp1;
                        }
                        return;
                    }
                }
                return;
            }
        }
        //到这里说明整个数组都是倒序了，反转一下便可
        for (int i = 0,j=nums.length-1; i<j ; i++,j--) {
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
        }

    }
    
}
