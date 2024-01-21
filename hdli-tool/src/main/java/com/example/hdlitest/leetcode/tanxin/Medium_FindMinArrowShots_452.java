package com.example.hdlitest.leetcode.tanxin;

import java.util.Arrays;

/**
 * 区间
 * @author luyi
 * @date 2024/1/21 17:52
 */
public class Medium_FindMinArrowShots_452 {

    /**
     * 用最少数量的箭引爆气球
     * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
     *
     * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
     *
     * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0){
            return 0;
        }
        //  根据气球直径的开始坐标从小到大排序
        //  使用Integer内置比较方法，不会溢出 a[0]可能是负数
        Arrays.sort(points,(a, b)->Integer.compare(a[0],b[0]));
        // points 不为空至少需要一支箭
        int result = 1;
        for(int i =1;i<points.length;i++){
            // 气球i和气球i-1不挨着，注意这里不是>=
            if(points[i][0] > points[i-1][1]){
                // 需要一支箭
                result++;
            }else{
                // 气球i和气球i-1挨着
                // 更新重叠气球最小右边界
                points[i][1] = Math.min(points[i][1],points[i-1][1]);
            }
        }
        return result;
    }
}
