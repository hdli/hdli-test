package com.example.hdlitest.leetcode.tanxin;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @author luyi
 * @date 2024/1/20 17:19
 */
public class Medium_ReconstructQueue_406 {

    /**
     * 根据身高重建队列:
     *
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     *
     * 思路：优先按身高降序排序，身高一样 按k升序排
     * 排序完的people： [[7,0], [7,1], [6,1], [5,0], [5,2]，[4,4]]
     *
     * 插入的过程：
     *
     * 插入[7,0]：[[7,0]]
     * 插入[7,1]：[[7,0],[7,1]]
     * 插入[6,1]：[[7,0],[6,1],[7,1]]
     * 插入[5,0]：[[5,0],[7,0],[6,1],[7,1]]
     * 插入[5,2]：[[5,0],[7,0],[5,2],[6,1],[7,1]]
     * 插入[4,4]：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        // 身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (a,b)->{
            if (a[0] == b[0]){
                // a - b 是升序排列，故在a[0] == b[0]的狀況下，会根据k值升序排列
                return a[1] - b[1];
            }
            //b - a 是降序排列，在a[0] != b[0]，的狀況会根据身高h值降序排列
            return b[0] - a[0];
        });
        LinkedList<int[]> que = new LinkedList<>();
        for (int [] p : people){
            //Linkedlist.add(index, value)，会将value插入到指定index里。
            que.add(p[1],p);
        }
        return que.toArray(new int[que.size()][]);
    }
}
