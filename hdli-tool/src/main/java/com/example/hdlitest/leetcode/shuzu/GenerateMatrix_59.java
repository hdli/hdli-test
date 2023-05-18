package com.example.hdlitest.leetcode.shuzu;

/**
 * @author luyi
 * @date 2023/5/8 10:59 PM
 */
public class GenerateMatrix_59 {

    /**
     * 螺旋矩阵 2
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        //控制循环的次数，螺旋的深度
        int loop = 0;
        int[][] res = new int[n][n];
        // 每次循环的开始点(start, start)
        int start = 0;
        // 定义填充数字
        int count = 1;
        int i, j;

        // 判断边界后，loop从1开始
        while (loop++ < n / 2) {
            // 模拟上侧从左到右
            for (j = start; j < n - loop; j++) {
                res[start][j] = count++;
            }
            // 模拟右侧从上到下
            for (i = start; i < n - loop; i++) {
                res[i][j] = count++;
            }
            // 模拟下侧从右到左
            for (; j >= loop; j--) {
                res[i][j] = count++;
            }
            // 模拟左侧从下到上
            for (; i >= loop; i--) {
                res[i][j] = count++;
            }
            start++;
        }
        //奇数
        if (n % 2 == 1) {
            res[start][start] = count;
        }

        return res;
    }





    public int[][] generateMatrix2(int n) {

        int[][] res = new int[n][n];

        int loop = 0;

        int count = 1;

        //每次循环开始的位置
        int start = 0;
        //int [wai][nei]
        int wai,nei;
        while (loop++ < n/2){
            //模拟上侧从左到右   1,2,3 (3不处理，交给右侧边处理)
            for (nei = start; nei < n-loop; nei++) {
                res[start][nei] = count++;
            }

            //上侧遍历后 nei已经等于 n-loop了  3,4,5(5不处理，交给下侧处理)
            for (wai = start; wai < n-loop; wai++) {
                res[wai][nei] = count++;
            }
            //模拟下侧从右到左  ，，每次跑一圈 每一行位置都会少一个
            for (; wai >= loop ; wai--) {
                res[wai][nei] = count++;
            }

            for (; nei >= loop ; nei--) {
                res[wai][nei] = count++;
            }
            start++;
        }
        //奇数
        if (n % 2 == 1) {
            res[start][start] = count;
        }


        return res;
    }

}
