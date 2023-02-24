package com.example.hdlitest.leetcode;

import java.util.Objects;

/**
 * @author luyi
 * @date 2023/1/16 11:35 下午
 */
public class AreSentencesSimilar_1813 {


    public static void main(String[] args) {
        String sentence1="My name is Haley";
        String sentence2 = "My Haley";
        System.out.println(areSentencesSimilar(sentence1, sentence2));
    }

    public static boolean solution(String sentence1, String sentence2){
        if (Objects.equals(sentence1,sentence2)){
            return false;
        }
        //谁短操作谁 comparisonStr长度大于operationStr长度
        String operationStr = null;
        String comparisonStr = null;
        if (sentence1.length() > sentence2.length()){
            operationStr = sentence2;
            comparisonStr = sentence1;
        }else {
            operationStr = sentence1;
            comparisonStr = sentence2;
        }
        String[] operationArray = operationStr.split(" ");
        String[] comparisonArray = comparisonStr.split(" ");

        int cha = comparisonArray.length - operationArray.length;

        int left = 0;
        int right = 0;

        for (int i = 0; i < comparisonArray.length; i++) {
            String c = comparisonArray[i];
            //下标溢出报错
            String o = operationArray[i];
            if (!Objects.equals(c,o)){
                break;
            }
            left++;
        }
        for (int i = comparisonArray.length-1; i >= 0; i--) {
            String c = comparisonArray[i];
            //下标溢出报错
            String o = operationArray[i];
            if (!Objects.equals(c,o)){
                break;
            }
            right++;
        }

        return left + right == operationArray.length;
    }

    /**
     * 双向指针
     * @param sentence1
     * @param sentence2
     * @return
     */
    public static boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");
        int i = 0, j = 0;
        while (i < words1.length && i < words2.length && words1[i].equals(words2[i])) {
            i++;
        }
        while (j < words1.length - i && j < words2.length - i && words1[words1.length - j - 1].equals(words2[words2.length - j - 1])) {
            j++;
        }
        return i + j == Math.min(words1.length, words2.length);
    }
}
