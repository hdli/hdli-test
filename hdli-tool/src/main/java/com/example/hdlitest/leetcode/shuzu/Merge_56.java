package com.example.hdlitest.leetcode.shuzu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luyi
 * @date 2023/5/5 8:20 PM
 */
public class Merge_56 {


    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] merge = merge(intervals);
        System.out.println(merge);
    }

    /**
     * 合并区间
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        //先排序
        Arrays.sort(intervals,(a,b)-> a[0]-b[0]);
        List<int[]> result = new ArrayList<>();
        int[] temp = null;
        for (int i = 0; i < intervals.length; i++) {
            if (temp == null){
                temp = intervals[i];
            }else {
                int a = temp[1];
                int b = intervals[i][0];
                if (a >= b){
                    //[[1,4],[2,3]] 这种情况取 4，结果 [[1,4]]
                    temp = new int[]{temp[0], Math.max(a,intervals[i][1])};
                }else {
                    //不存在交集，就把当前操作的数组放到结果集中，
//                    result[resultIndex] = temp;
                    result.add(temp);
                    //遍历的数组附给temp
                    temp = intervals[i];
                }
            }
        }
        if (temp != null){
            result.add(temp);
        }
        return result.toArray(new int[result.size()][]);
    }
}
