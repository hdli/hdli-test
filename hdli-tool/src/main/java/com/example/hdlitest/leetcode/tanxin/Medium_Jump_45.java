package com.example.hdlitest.leetcode.tanxin;

/**
 * @author luyi
 * @date 2024/1/19 00:42
 */
public class Medium_Jump_45 {

    /**
     * 跳跃游戏 II：最小跳跃次数
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        //当前的覆盖最大区域
        int curRange = 0;
        //下一个覆盖范围
        int nextRange = 0;
        int count = 0;
        for(int i = 0;i< nums.length;i++){
            nextRange = Math.max(nums[i]+i,nextRange);
            //已经走到当前覆盖范围头了
            if(i == curRange){
                //还没到终点
                if(curRange < nums.length-1){
                    //跳到下一个覆盖范围
                    curRange = nextRange;
                    count++;
                    //下一个覆盖范围，覆盖了终点
                    if(nextRange >= nums.length-1){
                        break;
                    }
                }else{
                    break;
                }
            }
        }
        return count;
    }
}
