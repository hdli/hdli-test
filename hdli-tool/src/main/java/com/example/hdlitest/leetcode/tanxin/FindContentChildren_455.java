package com.example.hdlitest.leetcode.tanxin;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author luyi
 * @date 2023/5/2 7:04 PM
 */
public class FindContentChildren_455 {


    public static void main(String[] args) {
        int [] g ={1,2,3};
        int [] s ={1,1};
        int contentChildren = findContentChildren(g, s);
        System.out.println(contentChildren);
    }

    /**
     * 分发饼干
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     *
     * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        //先把孩子的胃口与 平台大小排序
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;

        int index = s.length -1;
        //优先拿大瓶干 喂给 胃口大的小孩
        for (int i = g.length -1; i >= 0; i--) {
            if (index >= 0 && s[index] >= g[i]){
                result++;
                index--;
            }
        }
        return result;
    }
}
