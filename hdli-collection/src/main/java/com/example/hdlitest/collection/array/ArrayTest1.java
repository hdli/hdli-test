package com.example.hdlitest.collection.array;

import org.springframework.util.StopWatch;

/**
 * 获取数组中左边所有元素的和与右边所有元素和相等的元素下标
 * @author 李会东
 * @version 1.0
 * @date 2019-10-9 10:19
 */
public class ArrayTest1 {

    public static void main(String[] args) {
        int[] a = {9,2,5,4};
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("方法一test1()");
        test1(a);
        stopWatch.stop();
        stopWatch.start("方法二balanceIndex()");
        balanceIndex(a);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }

    //两次循环
    private static void test1(int[] a){
        if (a == null || a.length < 3){
            return;
        }
        int left = 0;
        int right = 0;
        //先获取除去第一位以外的其他元素的和
        for (int i = 1; i < a.length; i++) {
            right+=a[i];
        }
        for (int j = 0; j < a.length; j++) {
            //第一位和最后一位不参与比较
            if (j == 0 || j == a.length-1){
                continue;
            }
            //获取当前元素左边的所有元素之和
            left += a[j-1];
            //获取当前元素右边的所有元素之和
            right -= a[j];
            if (left == right){
                System.out.println("得到需求的元素下标："+j);
            }
        }
    }

    //仅用一次循环
    private static void balanceIndex(int[] data){
        if (data == null || data.length < 3) {
            return;
        }
        int total = data.length;
        int left = 0, right = 0;
        int i = 0, j = total - 1;
        while (i < j){
            if (left <= right){
                left += data[i];
                i++;
            }else {
                right += data[j];
                j--;
            }
            System.out.println("i="+i+" j="+j+" left="+left+" right="+right);

            // 此时的i和j相等且left和right都没有包含data[i]
            if (left == right && i == j){
                System.out.println("得到需求的元素下标："+i);
            }
        }
    }


}
