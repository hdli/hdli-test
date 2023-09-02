package com.example.hdlitest.collection.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 拿当前位置的数 与后面的数比较 获取到最小的 再替换位置
 * @author 李会东
 * @version 1.0
 * @date 2020-1-9 15:15
 */
public class SelectionSort {



    public static void main(String[] args) {
        selectSort2();
    }

    /**
     * 优化，先对比，找到当前对比中最小值的下标 k, 如果k!=i就替换
     */
    public static void selectSort2(){
        int[] arr = {1, 3, 2, 45, 65, 33, 12};
        System.out.println("交换之前：");
        System.out.println(Arrays.toString(arr));

        //选择排序的优化
        for (int i = 0; i < arr.length - 1; i++) {
            // 做第i趟排序
            int k = i;
            for (int j = i + 1; j < arr.length; j++) {
                // 选最小的记录
                if (arr[j] < arr[k]) {
                    //记下目前找到的最小值所在的位置
                    k = j;
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            if (i != k) {
                //交换a[i]和a[k]
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
        System.out.println();
        System.out.println("交换后：");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将未排序的第一个数字与剩余的每个数字进行对比，如果与预期的顺序不符，则换位
     */
    public static void selectSort(){
        int[] arr = {1, 3, 2, 45, 65, 33, 12};
        System.out.println("交换之前：");
        System.out.println(Arrays.toString(arr));

        for (int i = 0;i < arr.length-1;i++){
            for (int j = i+1;j< arr.length;j++){
                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println();
        System.out.println("交换后：");
        System.out.println(Arrays.toString(arr));
    }
}
