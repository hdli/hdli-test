package com.example.hdlitest.collection.sort;

import java.util.Arrays;

/**
 *
 *
 * 插入排序
 *
 * @author luyi
 * @date 2023/7/2 17:06
 */
public class InsertionSort {


    public static void main(String[] args) {
        insertionSort();
    }


    public static void insertionSort(){
        int[] arr = {6, 3, 8, 2, 9, 1};
        System.out.println("排序前数组为：");
        System.out.println(Arrays.toString(arr));
        System.out.println();

        //从1开始，左边是0，拿当前i位置上的元素与其左侧所有的数去比，比自己大就换位置
        /**
         * 第一次循环 i=1  value=6 找左侧比结果      [3, 6, 8, 2, 9, 1]
         *
         * 第二次循环 i=2  value=8 找左侧比结果 没有比8大
         *
         * 第三次循环 i=3  value=2 找左侧比结果      [3, 6, 2, 8, 9, 1]
         *                                       [3, 2, 6, 8, 9, 1]
         *                                       [2, 3, 6, 8, 9, 1]
         * 第四次循环 i=4  value=9 没有比8大
         *
         * 第五次循环 i=5  value=1                [2, 3, 6, 8, 1, 9]
         *                                      [2, 3, 6, 1, 8, 9]
         *                                      [2, 3, 1, 6, 8, 9]
         *                                      [2, 1, 3, 6, 8, 9]
         *                                      [1, 2, 3, 6, 8, 9]
         */
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0){
                if (arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    j--;
                    System.out.println(Arrays.toString(arr));
                }else {
                    //比自身小就不在比了，越向左越小
                    break;
                }
            }
            System.out.println();
        }
        System.out.println("排序前数组为：");
        System.out.println(Arrays.toString(arr));
    }

}
