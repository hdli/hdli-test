package com.example.hdlitest.collection;

import java.util.BitSet;

/**
 * @author Lihuidong
 * @version 1.0
 * @date 2020/2/4 23:25
 */
public class BitSetDemo {

    /**
     * 求一个字符串包含的char
     */
    public static void containChars(String str) {

        BitSet used = new BitSet();

        for (int i = 0; i < str.length(); i++) {
            // set bit for char
            used.set(str.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int size = used.size();
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            if (used.get(i)) {
                sb.append((char) i);
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        //BitSet使用示例
        BitSetDemo.containChars("How do you do? 你好呀");
    }
}
