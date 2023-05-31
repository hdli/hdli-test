package com.example.hdlitest.leetcode.shuzu;

/**
 * @author luyi
 * @date 2023/5/27 18:38
 */
public class Search_33 {


    /**
     * 搜索旋转排序数组
     * 1、获取中间值后，先判断左右两侧那边是有序的，
     *  判断中间值与nums[r]
     *           m > num[r] 左侧 0 - mid 升序
     *           m < num[r] 右侧 mid+1 - nums.length 升序
     *  知道升序空间后，判断target那一侧
     *
     *
     *
     *
     * 二分查找法
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target){
        if(nums.length == 0){
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r){
            int mid = (l+r)/2;
            int m = nums[mid];
            if (m == target){
                return mid;
            }
            if (m <= nums[r]){
                //8，9，1，2，3，4，5，6，7
                //右侧mid+1 到 r为升序   target <= nums[r]确保target没有超出最右侧编辑 超出右边边界了，可能就在左侧了
                if (m < target && target <= nums[r]){ // 这里只需要确保target在右侧升序的区间，就增加l
                    l = mid+1;
                }else {
                    r = mid-1;
                }
            }else {
                // 5，6，7，8，9，1，2，3，4
                //左侧l 到 mid 为升序  target >= nums[l]确保target没有超出左侧边界
                if (m > target && target >= nums[l]){  //这里只需要确保target在左侧升序的区间，就缩短r
                    r = mid-1;
                }else {
                    l=mid+1;
                }
            }
        }

        return -1;
    }



    /**
     * 找到k,后续的是升序，直接 数组二分法
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if(nums.length == 0){
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==target){
                return i;
            }
            if (i != 0 && nums[i] < nums[i-1]){
                int l = i;
                int r = nums.length - 1;
                while (l<=r){
                    int m = (l + r)/2;
                    if(nums[m] > target){
                        r = m-1;
                    }else if (nums[m] < target){
                        l = m +1;
                    }else{
                        return m;
                    }
                }
                return -1;
            }
        }
        return -1;
    }
}
