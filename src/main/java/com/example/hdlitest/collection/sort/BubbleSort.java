package com.example.hdlitest.collection.sort;

/**
 * 冒泡排序
 *
 * @author 李会东
 * @version 1.0
 * @date 2020-1-9 15:11
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {6, 3, 8, 2, 9, 1};
        System.out.println("排序前数组为：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        //这里for循环表示总共需要比较多少轮
        for (int i = 1; i < arr.length; i++) {
            //设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列	已经有序，排序已经完成。
            boolean flag = true;
            //这里for循环表示每轮比较参与的元素下标
            //对当前无序区间array[0......length-i]进行排序
            //j的范围很关键，这个范围是在逐步缩小的,因为每轮比较都会将最大的放在右边
            //内层循环控制每一趟排序多少次
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println();
        System.out.println("排序后的数组为：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

}
