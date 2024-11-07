package com.example.hdlitest.collection.sort;

import java.util.Arrays;

/**
 * 堆排序
 * @author luyi
 * @date 2024/11/1 16:38
 */
public class HeapSort {


    //下标为i的节点 的父节点 下标 ：(i-1)/2
    //下标为i的节点 的左孩子 下标： i*2+1
    //下标为i的节点 的右孩子 下标： i*2+2

    /**
     * 堆排序：时间复杂度 nlogn
     * @param args
     */
    public static void main(String[] args) {
        int [] nums = new int[]{2,5,3,9,6,7,1};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 大顶堆
     * 调整 指定位置
     * @param nums
     * @param n    数组长度
     * @param i    要调整的位置
     */
    public static void heapify(int [] nums,int n,int i){
        int largestIndex = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        if (right < n && nums[right] > nums[largestIndex]){
            largestIndex = right;
        }
        if (left < n && nums[left] > nums[largestIndex]){
            largestIndex = left;
        }
        if (largestIndex != i){
            swap(nums,i,largestIndex);
            heapify(nums,n,largestIndex);
        }
    }

    /**
     * 小顶堆调整
     * @param nums
     * @param n
     * @param i
     */
    public static void heapify2(int [] nums,int n,int i){
        int leastIndex = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        if (right < n && nums[right] < nums[leastIndex]){
            leastIndex = right;
        }
        if (left < n && nums[left] < nums[leastIndex]){
            leastIndex = left;
        }
        if (leastIndex != i){
            swap(nums,i,leastIndex);
            heapify2(nums,n,leastIndex);
        }
    }


    /**
     * 构建堆
     * @param nums
     */
    public static void buildHeap(int [] nums){
        //调整每一个头节点
        for (int i = (nums.length-1)/2 ; i >= 0;i--){
            heapify(nums,nums.length,i);
        }
    }


    public static void heapSort(int [] nums){
        //建堆
        buildHeap(nums);
        //堆排序 大顶堆 堆顶最大
        for (int i = nums.length-1;i > 0;i--){
            swap(nums,i,0);
            heapify(nums,i,0);
        }

    }


    public static void swap(int [] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
