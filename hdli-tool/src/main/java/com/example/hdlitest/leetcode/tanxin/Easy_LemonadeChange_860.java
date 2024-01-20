package com.example.hdlitest.leetcode.tanxin;

/**
 * @author luyi
 * @date 2024/1/20 16:56
 */
public class Easy_LemonadeChange_860 {


    /**
     * 柠檬水找零:在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     *
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     *
     * 注意，一开始你手头没有任何零钱。
     *
     * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     *
     *
     *
     * 思路：只需要维护三种金额的数量，5，10和20。
     * 有如下三种情况：
     *
     * 情况一：账单是5，直接收下。
     * 情况二：账单是10，消耗一个5，增加一个10
     * 情况三：账单是20，优先消耗一个10和一个5，如果不够，再消耗三个5
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0,ten = 0,twenty=0;
        for (Integer b : bills){
            if (b == 5){
                five++;
            }
            if (b == 10){
                if (five == 0){
                    return false;
                }else {
                    five--;
                    ten++;
                }
            }
            if (b == 20){
                if (ten >= 1 && five >= 1){
                    twenty++;
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five-=3;
                    twenty++;
                }else {
                    return false;
                }
            }
        }
        return true;
    }
}
