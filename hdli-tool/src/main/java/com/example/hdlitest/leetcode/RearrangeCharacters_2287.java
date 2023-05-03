package com.example.hdlitest.leetcode;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author luyi
 * @date 2023/1/13 11:46 上午
 */
public class RearrangeCharacters_2287 {

    /**
     * 给你两个下标从 0 开始的字符串 s 和 target 。你可以从 s 取出一些字符并将其重排，得到若干新的字符串。
     *
     * 从 s 中取出字符并重新排列，返回可以形成 target 的 最大 副本数。
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = "cqlcqrqzqrampbumlixalzclhxvudysxykeblrkufyvowpufzecwyobjgbzgbkjqmrpibyugjlybukidzlnmxomkfpwp";
        String target = "zqizcx";
        int solution = solution(s, target);
        System.out.println(solution);
    }

    /**
     * 失败
     * 考虑了最小匹配，但是其他元素
     * @param s
     * @param target
     * @return
     */
    public static int solution(String s, String target){
        Map<String,Integer> map = new HashMap<>(target.length());
        Map<String,Integer> taMap = new HashMap<>(target.length());
        int nums = Math.max(s.length(), target.length());
        for (int i = 0; i < nums; i++) {
            if (i < s.length()){
                String a = String.valueOf(s.charAt(i));
                if (target.contains(a)){
                    map.merge(a, 1, Integer::sum);
                }
            }
            if (i < target.length()){
                String c = String.valueOf(target.charAt(i));
                taMap.merge(c, 1, Integer::sum);
            }
        }
        if (map.isEmpty()){
            return 0;
        }
        //target中有元素不在s中
        if (map.size() != taMap.size()){
            return 0;
        }
        Integer ant = null;
        for (Map.Entry<String, Integer> e : map.entrySet()){
            int res = e.getValue()/taMap.get(e.getKey());
            //如果s中有1和元素，但是target需要2个元素，那就不会匹配
            if (res == 0){
                return 0;
            }
            //取最小
            if (ant == null){
                ant = res;
            }else {
                ant = res < ant ? res:ant;
            }
        }
        return ant;
    }
}
