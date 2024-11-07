package com.example.hdlitest.leetcode.paixu;

/**
 * @author luyi
 * @date 2024/10/30 17:49
 */
public class FindKthLargest_215 {


    /**
     * 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     *
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     *
     * @param nums
     * @param k
     * @return
     */
    public  int findKthLargest(int[] nums, int k) {

//        return quickSort(nums,0,nums.length,k);
        return heapSort(nums,k);
    }


    public static void main(String[] args) {
        int [] nums = new int[]{3,2,1,5,6,4};
        int i = heapSort(nums, 2);
        System.out.println(i);
    }





    /**
     * 使用快排方式
     * @param nums
     * @param left
     * @param right
     * @param k
     * @return
     */
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


    /**
     * 堆排序
     * @param nums
     * @param k
     * @return
     */
    public static int heapSort(int [] nums,int k){
        //构建大顶堆
        for (int i = (nums.length-1) / 2 ; i >= 0 ; i--){
            heapify(nums,nums.length,i);
        }
        //堆排序
        for (int i = nums.length-1 ; i > (nums.length - k) ; i--){
            swap(nums,i,0);
            heapify(nums,i,0);
        }
        return nums[0];
    }


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
            swap(nums,largestIndex,i);
            heapify(nums,n,largestIndex);
        }
    }
}
