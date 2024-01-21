package com.example.hdlitest.leetcode.tanxin;

import java.util.Arrays;

/**
 * 区间
 * @author luyi
 * @date 2024/1/21 18:11
 */
public class Medium_EraseOverlapIntervals_435 {
    /**
     * 无重叠区间
     * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0){
            return 0;
        }
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        int result = 0;
        for (int i = 1; i < intervals.length;i++){
            //i和 i-1有重合，
            if (intervals[i][0] < intervals[i-1][1]){
                result++;
                //去掉右边更大的，留下的就是右边更小的，去和下一个比
                intervals[i][1] = Math.min(intervals[i][1],intervals[i-1][1]);
            }
        }
        return result;
    }


    public int eraseOverlapIntervals2(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> {
            return Integer.compare(a[0],b[0]);
        });
        int remove = 0;
        int pre = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(pre > intervals[i][0]) {
                //有重合
                remove++;
                //去掉右边更大的，留下的就是右边更小的，去和下一个比
                pre = Math.min(pre, intervals[i][1]);
            } else{
                //没重合
                pre = intervals[i][1];
            }
        }
        return remove;
    }
}
