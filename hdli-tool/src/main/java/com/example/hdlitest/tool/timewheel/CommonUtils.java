package com.example.hdlitest.tool.timewheel;

/**
 * @author luyi
 * @date 2024/9/5 11:38
 */
public class CommonUtils {

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 将大小格式化为 2的N次
     * @param cap 初始大小
     * @return 格式化后的大小，2的N次
     */
    public static int formatSize(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }


    public static void main(String[] args) {
        System.out.println(formatSize(1));
    }
}
