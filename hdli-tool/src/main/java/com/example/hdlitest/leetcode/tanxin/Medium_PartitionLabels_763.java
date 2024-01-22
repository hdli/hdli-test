package com.example.hdlitest.leetcode.tanxin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyi
 * @date 2024/1/22 22:48
 */
public class Medium_PartitionLabels_763 {

    /**
     * 划分字母区间：
     * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
     *
     * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
     *
     * 返回一个表示每个字符串片段的长度的列表。
     *
     *
     * 思路：
     * 在遍历的过程中相当于是要找每一个字母的边界，如果找到之前遍历过的所有字母的最远边界，说明这个边界就是分割点了。此时前面出现过所有字母，最远也就到这个边界了。
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        //统计每一个字符最后出现的位置
        int [] edge = new int[26];
        char [] chars = s.toCharArray();
        for(int i = 0;i<chars.length;i++){
            edge[chars[i]-'a']=i;
        }
        //从头遍历字符，并更新字符的最远出现下标，如果找到字符最远出现位置下标和当前下标相等了，则找到了分割点
        int start = 0;
        int last = 0;
        for(int i = 0; i< chars.length;i++){
            last = Math.max(edge[chars[i]-'a'],last);
            //如果找到字符最远出现位置下标和当前下标相等了，则找到了分割点
            if(i == last){
                list.add(last-start+1);
                start = i+1;
            }
        }
        return list;
    }
}
