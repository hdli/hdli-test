package com.example.hdlitest.leetcode.tanxin;

/**
 * @author luyi
 * @date 2024/1/18 23:33
 */
public class Medium_WiggleMaxLength_376 {
    /**
     * 摆动序列：
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1){
            return nums.length;
        }
        //上一个差值
        int preDiff = 0;
        //当前差值
        int curDiff = 0;
        // 记录峰值个数，序列默认序列最右边有一个峰值
        int count = 1;
        for (int i = 0; i < nums.length -1;i++){
            //得到当前差值
            curDiff = nums[i+1]-nums[i];
            //如果当前差值和上一个差值为一正一负
            //等于0的情况表示初始时的preDiff   平坡
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)){
                count++;
                preDiff = curDiff;
            }
        }
        return count;
    }
}
