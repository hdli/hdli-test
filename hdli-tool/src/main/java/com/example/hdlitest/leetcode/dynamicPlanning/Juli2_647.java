package com.example.hdlitest.leetcode.dynamicPlanning;

/**
 * @author luyi
 * @date 2024/2/21 22:23
 */
public class Juli2_647 {

    /**
     * 回文子串:
     * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
     *
     * 回文字符串 是正着读和倒过来读一样的字符串。
     *
     * 子字符串 是字符串中的由连续字符组成的一个序列。
     *
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     *
     *
     * 1、确定dp数组（dp table）以及下标的含义
     * 布尔类型的dp[i][j]：表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串，如果是dp[i][j]为true，否则为false。
     * 2、确定递推公式
     * 在确定递推公式时，就要分析如下几种情况。
     *
     * 整体上是两种，就是s[i]与s[j]相等，s[i]与s[j]不相等这两种。
     *
     * 当s[i]与s[j]不相等，那没啥好说的了，dp[i][j]一定是false。
     *
     * 当s[i]与s[j]相等时，这就复杂一些了，有如下三种情况
     * .情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
     * .情况二：下标i 与 j相差为1，例如aa，也是回文子串
     * .情况三：下标：i 与 j相差大于1的时候，例如cabac，此时s[i]与s[j]已经相同了，我们看i到j区间是不是回文子串就看aba是不是回文就可以了，那么aba的区间就是 i+1 与 j-1区间，这个区间是不是回文就看dp[i + 1][j - 1]是否为true。
     *
     * 3、dp数组如何初始化
     * dp[i][j]初始化为false。
     *
     * 4、确定遍历顺序
     * 首先从递推公式中可以看出，情况三是根据dp[i + 1][j - 1]是否为true，在对dp[i][j]进行赋值true的。
     * 所以一定要从下到上，从左到右遍历，这样保证dp[i + 1][j - 1]都是经过计算的。
     *
     * **********
     * 本题其实：从最后一个字符开始为中心，两边扩散判断是否存在回文串
     * **********
     *
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        boolean [][] dp = new boolean[s.length()][s.length()];
        //初始化都是false

        int result = 0;
        for (int i = s.length()-1; i >= 0;i--){
            for (int j = i;j < s.length(); j++){
                if (s.charAt(i) == s.charAt(j)){
                    //1、j-i = 0 下标i 与 j相同，同一个字符例如a，当然是回文子串
                    //2、j-i = 1 下标i 与 j相差为1，例如aa，也是回文子串
                    if (j-i <= 1){
                        dp[i][j] = true;
                        result++;
                    } else if (dp[i + 1][j - 1]) {
                        //下标：i 与 j相差大于1的时候，例如cabac，
                        // 此时s[i]与s[j]已经相同了，我们看i到j区间是不是回文子串就看aba是不是回文就可以了，
                        // 那么aba的区间就是 i+1 与 j-1区间，这个区间是不是回文就看dp[i + 1][j - 1]是否为true。
                        dp[i][j] = true;
                        result++;
                    }
                }
            }
        }
        return result;
    }
}
