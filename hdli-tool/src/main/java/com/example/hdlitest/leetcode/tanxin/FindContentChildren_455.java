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
