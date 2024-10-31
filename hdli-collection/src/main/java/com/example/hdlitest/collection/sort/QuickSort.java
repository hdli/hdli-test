package com.example.hdlitest.collection.sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * 快速排序
 * @author luyi
 * @date 2024/10/30 15:56
 */
public class QuickSort {


    public static void main(String[] args) {
        int[] arr = {6, 3, 8, 2, 9, 1};
        System.out.println("排序前数组为：");
        System.out.println(Arrays.toString(arr));
//        quickSort(arr,0,arr.length-1);
        quickSort2(arr);
        System.out.println("排序后数组为：");
        System.out.println(Arrays.toString(arr));
    }


    public static void quickSort(int[] arr, int left, int right){
        if (left < right){
            int mid = partition2(arr,left,right);
            quickSort(arr,left,mid-1);
            quickSort(arr,mid+1,right);
        }
    }

    /**
     * 非递归
     * 快速排序我们知道在排序中因为是对区间的子区间进行单趟排序，循环直到区间是一个数，我么们是利用递归来处理，
     * 可是这也引发了一个问题：数据足够大，递归的深度太深，就会出现栈溢出的问题，此时排序就崩了，
     * 因此递归有它的好处，也存在他的隐患。
     * @param arr
     */
    public static void quickSort2(int[] arr){
        if (arr.length == 0 || arr.length == 1){
            return;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(arr.length-1);
        while (!stack.isEmpty()){
            int r = stack.pop();
            int l = stack.pop();
            if (l < r){
                int i = partition2(arr, l, r);
                stack.push(l);
                stack.push(i-1);
                stack.push(i+1);
                stack.push(r);
            }
        }
    }


    /**
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int partition(int[] arr, int left, int right) {
        //选左边为基准值
        int pivot = arr[left];
        int i = left;
        int j = right;
        while(i < j){
            //左边为key，右边先走，相遇值比key小；右边为key，左边先走，相遇值比key大
            while (i < j && arr[j] >= pivot){
                j--;
            }
            while (i < j && arr[i] <= pivot){
                i++;
            }
            if (i < j){
                swap(arr,i,j);
            }
        }
        swap(arr,left,i);
        return i;
    }



    /**
     * 前后指针法
     * 顾名思义，给了两个可以想象成指针的标识，其中cur用来探路找小，cur位置的从自身开始，遇到比key小的此时，prev++，
     * 即prev出发一步，之后交换prev与cur的位置的值，cur继续前进。
     *
     * 1.最开始cur与prev是相邻的、
     *
     * 2.当遇到大key的时候，cur继续走，两者之间的数都是比key大的，当cur开始出发遇到比key小的值时，会和++prev交换位置，此时大的数就被往右边推，而小于的都被换到左边（我们可以看到cur与prev之间都是比key大的，而之后cur一遇到小的，prev此时指向大的，两者换位。实现左小右大）。
     *
     * 3.当cur走完时，此时prev的位置就是key的位置。
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int partition2(int[] arr, int left, int right) {
        int pre = left;
        int cur = left+1;
        int pivot = arr[left];
        while (cur <= right){
            if (arr[cur] < pivot){
                pre++;
                if (pre < cur){
                    swap(arr,pre,cur);
                }
            }
            cur++;
        }
        swap(arr,left,pre);
        return pre;
    }



    public static void swap(int [] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
