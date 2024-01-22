package com.example.hdlitest.leetcode.tanxin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 区间
 * @author luyi
 * @date 2024/1/22 23:10
 */
public class Medium_Merge_56 {
    /**
     * 合并区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0){
            return intervals;
        }
        //先排序，让所有的相邻区间尽可能的重叠在一起，按左边界
        Arrays.sort(intervals,(a, b)->Integer.compare(a[0],b[0]));
        //其实就是用合并区间后左边界和右边界，作为一个新的区间，加入到result数组里就可以了。如果没有合并就把原区间加入到result数组。
        LinkedList<int[]> result = new LinkedList<>();
        result.add(intervals[0]);
        for(int i = 1;i<intervals.length;i++){
            if(intervals[i][0] <= result.getLast()[1]){
                //有重叠，合并区间，只需要动 右边界
                result.getLast()[1] = Math.max(intervals[i][1],result.getLast()[1]);
            }else{
                result.add(intervals[i]);
            }
        }
        return result.toArray(new int[result.size()][]);

    }

}
