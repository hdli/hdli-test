package com.example.hdlitest.leetcode.paixu;

/**
 * @author luyi
 * @date 2024/10/30 17:49
 */
public class FindKthLargest_215 {



    public int findKthLargest(int[] nums, int k) {

        quickSort(nums,0,nums.length,k);
        return 0;
    }

    private static int quickSort(int [] nums,int left,int right,int k){
        int pivot = nums[left];
        int i = left;
        int j = right;
        while(i < j){
            while (i < j && nums[j] >= pivot){
                j--;
            }
            while (i < j && nums[i] <= pivot){
                i++;
            }
            if (i < j){
                swap(nums,i,j);
            }
        }
        swap(nums,left,i);
        if (i == (nums.length-k)){
            return pivot;
        }
        if (i > (nums.length-k)){
            return quickSort(nums,left,i-1,k);
        }else {
            return quickSort(nums,i+1,right,k);
        }
    }

    /**
     * 快排 使用前后指针定位
     * @param nums
     * @param left
     * @param right
     * @param k
     * @return
     */
    private static int quickSort2(int [] nums,int left,int right,int k){
        int pre = left;
        int cur = left +1;
        int pivot = nums[left];
        while (cur <= right){
            if (nums[cur] < pivot){
                pre++;
                if (pre < cur){
                    swap(nums,pre,cur);
                }
            }
            cur++;
        }
        if (pre == (nums.length-k)){
            return pivot;
        }
        swap(nums,left,pre);

        if (pre > (nums.length-k)){
            return quickSort2(nums,left,pre-1,k);
        }else {
            return quickSort2(nums,pre+1,right,k);
        }
    }


    public static void swap(int [] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
