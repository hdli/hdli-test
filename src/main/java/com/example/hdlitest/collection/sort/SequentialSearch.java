package com.example.hdlitest.collection.sort;

import java.util.Scanner;

/**
 * 顺序查找
 *
 * @author 李会东
 * @version 1.0
 * @date 2020-1-9 15:18
 */
public class SequentialSearch {

    public static void main(String[] args) {
        int[] a = {4, 6, 2, 8, 1, 9, 0, 3};
        Scanner input = new Scanner(System.in);

        System.out.println("请输入你要查找的数：");
        //存放控制台输入的语句

        int num = input.nextInt();
        //调用searc()方法，将返回值保存在result中
        int result = search(a, num);
        if (result == -1) {
            System.out.println("你输入的数不存在与数组中。");
        } else {
            System.out.println("你输入的数字存在，在数组中的位置是第：" + (result + 1) + "个");
        }
    }

    /**
     * 顺序查找
     *
     * @param a
     * @param num
     * @return
     */
    public static int search(int[] a, int num) {
        for (int i = 0; i < a.length; i++) {
            //如果数据存在
            if (a[i] == num) {
                //返回数据所在的下标，也就是位置
                return i;
            }
        }
        //不存在的话返回-1
        return -1;
    }

    /**
     * 二分查找算法
     *
     * @param arr  要先对数组升序排序
     * @param num
     * @return
     */
    public static int binarySearch(int[] arr, int num) {
        int low = 0;
        int upper = arr.length - 1;
        while (low <= upper) {
            int mid = (upper + low) / 2;
            if (arr[mid] < num) {
                low = mid + 1;
            } else if (arr[mid] > num) {
                upper = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
