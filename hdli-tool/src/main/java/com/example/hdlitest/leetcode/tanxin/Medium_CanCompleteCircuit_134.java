package com.example.hdlitest.leetcode.tanxin;

/**
 * @author luyi
 * @date 2024/1/20 16:18
 */
public class Medium_CanCompleteCircuit_134 {


    /**
     * 加油站：
     * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     *
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     *
     * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int index = 0;
        for (int i = 0;i<gas.length;i++){
            curSum+=gas[i]-cost[i];
            totalSum+=gas[i]-cost[i];
            //i从0开始累加rest[i]，和记为curSum，一旦curSum小于零，说明[0, i]区间都不能作为起始位置，因为这个区间选择任何一个位置作为起点，到i这里都会断油，那么起始位置从i+1算起，再从0计算curSum。
            if (curSum < 0){
                index = (i + 1)%gas.length;
                curSum = 0;
            }
        }
        //首先如果总油量减去总消耗小于零那么一定不可以跑完一圈；
        if (totalSum < 0){
            return -1;
        }
        return index;
    }
}
