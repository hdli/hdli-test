package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/1/27 16:58
 */
public class BagProblem_01 {


    /**
     *  背包：
     *  常见的背包类型主要有以下几种：
     * 1、0/1背包问题：每个元素最多选取一次  *
     * 2、完全背包问题：每个元素可以重复选择  *
     * 3、组合背包问题：背包中的物品要考虑顺序
     * 4、分组背包问题：不止一个背包，需要遍历每个背包
     *
     * 而每个背包问题要求的也是不同的，按照所求问题分类，又可以分为以下几种：
     * 1、最值问题：要求最大值/最小值
     * 2、存在问题：是否存在…………，满足…………
     * 3、组合问题：求所有满足……的排列组合
     *
     *
     * 然后是问题分类的模板：
     * 1、最值问题: dp[i] = max/min(dp[i], dp[i-nums]+1)或dp[i] = max/min(dp[i], dp[i-num]+nums);
     * 2、存在问题(bool)：dp[i]=dp[i]||dp[i-num];
     * 3、组合问题：dp[i]+=dp[i-num];
     *
     *
     */
    public static void main(String[] args) {
        int[] weight = {1,3,4};
        int[] value = {15,20,30};
        int bagSize = 4;
//        test(weight, value,bagSize,0);
        dp(weight,value,bagSize);
    }
    static int maxValue = 0;
    static int currentValue = 0;

    /**
     * 暴力破解，回溯算法
     * @param weight
     * @param value
     * @param bagSize
     * @param startIndex
     */
    private static void test(int[] weight,int[] value,int bagSize,int startIndex){
        if (bagSize == 0){
            maxValue = Math.max(maxValue, currentValue);
            return;
        }
        for (int i = startIndex; i < weight.length; i++){
            if (weight[i] > bagSize){
                continue;
            }
            bagSize-=weight[i];
            currentValue+=value[i];
            test(weight,value,bagSize,i+1);
            currentValue-=value[i];
            bagSize+=weight[i];
        }
    }


    /**
     * 动态规划 ：使用二维数组
     * @param weight
     * @param value
     * @param bagSize
     */
    private static void dp(int[] weight,int[] value,int bagSize){
        //确定dp数组以及下标的含义:dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少。
        int [][] dp = new int[weight.length][bagSize+1];

        //dp数组如何初始化：二维数组的第一列，j=0 背包容量是0，不管什么物品都方不进入 最大价值=0；
        for (int i = 0; i < weight.length;i++){
            dp[i][0] = 0;
        }
        //dp数组如何初始化：二维数组的第一排，i=0 放物品0，从j=物品0的重量开始，都需的j(背包重量都可以放下)，初始化为物品0的价值
        for (int j = weight[0]; j <= bagSize;j++){
            dp[0][j] = value[0];
        }
        //递推公式 每个物品都只有俩选项，放 与 不放
        for (int i = 1; i < weight.length;i++){   //商品
            for (int j = 1;j<= bagSize;j++){      //背包
                if (j < weight[i]){
                    /**
                     * 当前背包的容量都没有当前物品i大的时候，是不放物品i的
                     * 那么前i-1个物品能放下的最大价值就是当前情况的最大价值
                     */
                    dp[i][j] = dp[i-1][j];
                }else {
                    /**
                     * 当前背包的容量可以放下物品i
                     * 那么此时分两种情况：
                     *    1、不放物品i   dp[i-1][j]
                     *    2、放物品i    dp[i-1][j-weight[i]]+value[i]
                     *                      !!!! j - weight[i]，这里可以理解为背包需要留出这个物品i的容量才可以放物品i
                     *
                     *                          当i放进去时，那么这时候整个物品集就被分成两部分，1到i-1和第i个，而这是i是确定要放进去的，
                     *                          那么就把j空间里的wi给占据了，只剩下j－wi的空间给前面i－1，那么只要这时候前面i－1在j－wi空间里构造出最大价值，即dp【i－1】【j－wi】，再加上此时放入的i的价值vi，就是dpij了
                     * 比较这两种情况下，哪种背包中物品的最大价值最大
                     */
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight[i]]+value[i]);
                }
            }
        }
        printDp(dp);
     }


    /**
     * 动态规划 ：使用一维数组
     * 滚动数组，其实在前面的题目中我们已经用到过滚动数组了，就是把二维dp降为一维dp，
     *      在使用二维数组的时候，递推公式：dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
     *      其实可以发现如果把dp[i - 1]那一层拷贝到dp[i]上，表达式完全可以是：dp[i][j] = max(dp[i][j], dp[i][j - weight[i]] + value[i]);
     *      与其把dp[i - 1]这一层拷贝到dp[i]上，不如只用一个一维数组了，只用dp[j]（一维数组，也可以理解是一个滚动数组）。
     *
     * @param weight
     * @param value
     * @param bagSize
     */
    private static void dp2(int[] weight,int[] value,int bagSize){
        //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值
        int [] dp = new int[bagSize+1];
        dp[0]=0;
        //遍历顺序：先遍历物品，再遍历背包容量    如果翻过来，先背包 再物品，结果就是每个dp[j]就只会放入一个物品
        for (int i = 0; i < weight.length;i++){
            //倒序遍历是为了保证物品i只被放入一次
            for (int j = bagSize; j>= weight[i];j--){
                dp[j] = Math.max(dp[j],dp[j-weight[i]]+value[i]);
            }
            printDp(dp);
        }
    }

    /**
     * 完全背包的动态规划
     * 物品无限个
     * @param weight
     * @param value
     * @param bagSize
     */
    private static void dp3(int[] weight,int[] value,int bagSize){
        int [] dp = new int[bagSize+1];
        dp[0]=0;
        for (int i = 0; i < weight.length;i++){
            for (int j = weight[i];j <= bagSize;j++){
                dp[j] = Math.max(dp[j],dp[j-weight[i]]+value[i]);
            }
            printDp(dp);
        }
    }

    /**
     * 完全背包
     * 二维数据 与 01背包的差异 第一行的初始化
     * @param weight
     * @param value
     * @param bagSize
     */
    private static void dp4(int[] weight,int[] value,int bagSize){
        int [][] dp = new int[weight.length][bagSize+1];
        //初始化第一行
        for (int j = weight[0];j<= bagSize;j++){
            int b = j/weight[0];
            dp[0][j] = b*value[0];
        }
        for(int i =1 ;i < weight.length;i++){
            for (int j = 1;j<= bagSize;j++){
                if (j < weight[i]){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight[i]]+value[i]);
                }
            }
        }

        printDp(dp);
    }


    public static void printDp(int [][] dp){
        for (int i = 0;i<dp.length;i++){
            for (int j = 0;j<dp[0].length;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void printDp(int [] dp){
        for (int i = 0;i<dp.length;i++){
            System.out.print(dp[i]+" ");
        }
        System.out.println();
    }

}
