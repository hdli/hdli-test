package com.example.hdlitest.leetcode.shuzu;

/**
 * @author luyi
 * @date 2023/5/6 11:09 PM
 */
public class MinSubArrayLen_209 {


    public static void main(String[] args) {
        int taget = 213;
        int[] nums= {12,28,83,4,25,26,25,2,25,25,25,12};
        minSubArrayLen2(taget,nums);
    }

    /**
     * 长度最小的子数组
     *
     * 失败
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        //先找出当前数组中的最大值，判断与target的大小
        //如果最大值都小于target的话，最大值，向左获取一个和判断，向右获取一个和判断。取左右时注意下标越界
        //  左边和右边先加最大的，判断是否大于，再加小的判断
        int maxIndex = 0;
        int maxValue = nums[0];
        for (int i = 1;i < nums.length;i++){
            if (nums[i] > maxValue){
                maxValue = nums[i];
                maxIndex = i;
            }
        }
        if (maxValue >= target){
            return 1;
        }
        int left = maxIndex -1;
        int right = maxIndex +1;
        int sum = maxValue;
        int minResult = 1;
        while (left >= 0 || right < nums.length){
            Integer leftValue = null;
            Integer rightValue = null;
            if (left >= 0){
                leftValue = nums[left];
                left--;
            }
            if (right < nums.length){
                rightValue = nums[right];
                right++;
            }
            if (leftValue == null){
                sum += rightValue;
                minResult++;
                if (sum >= target){
                    return minResult;
                }
            }
            if (rightValue == null) {
                sum += leftValue;
                minResult++;
                if (sum >= target){
                    return minResult;
                }
            }
            if (rightValue != null && leftValue != null){
                int max = Math.max(leftValue, rightValue);
                sum += max;
                minResult++;
                if (sum >= target){
                    return minResult;
                }
                int min = Math.min(leftValue, rightValue);
                sum += min;
                minResult++;
                if (sum >= target){
                    return minResult;
                }
            }
        }
        return 0;
    }

    /**
     *
     * 长度最小的子数组
     *
     * 滑动窗口
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen2(int target, int[] nums) {
        int start = 0;
        int minResult = nums.length;
        int sum = 0;
        boolean haveMin = false;
        for (int end = 0; end < nums.length; end++) {
            if (nums[end] >= target){
                return 1;
            }
            sum+=nums[end];
            while (sum >= target){
                haveMin = true;
                int length = end - start +1;
                minResult = Math.min(minResult,length);
                sum = sum - nums[start];
                //窗口开始位置移动
                start++;
            }
        }
        return haveMin ? minResult : 0;
    }


}
