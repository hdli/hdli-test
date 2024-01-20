package com.example.hdlitest.leetcode.tanxin;

import java.util.Arrays;

/**
 * @author luyi
 * @date 2024/1/20 18:24
 */
public class Medium_LeastInterval_621 {

    /**
     * 任务调度器，二刷
     *给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
     *
     * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     *
     * 你需要计算完成所有任务所需要的 最短时间 。
     *
     * 思路：容易想到的一种贪心策略为：先安排出现次数最多的任务，让这个任务两次执行的时间间隔正好为n。再在这个时间间隔内填充其他的任务
     * https://leetcode.cn/problems/task-scheduler/solutions/509866/jian-ming-yi-dong-de-javajie-da-by-lan-s-jfl9
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int [] buckets = new int[26];
        for (int i = 0; i < tasks.length;i++){
            buckets[tasks[i] - 'A']++;
        }
        Arrays.sort(buckets);
        //1、得到 数组中出现次数最多的字母出现的次数
        int maxTimes = buckets[25];
        //2、得到 出现次数最多的字母个数
        int maxCount = 1;
        for (int i = 25; i >= 1;i--){
            if (buckets[i] == buckets[i-1] ){
                maxCount++;
            }else {
                break;
            }
        }
        int result = (maxTimes-1)*(n+1)+maxCount;
        return Math.max(result,tasks.length);

    }
}
