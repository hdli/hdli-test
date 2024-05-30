package com.example.hdlitest.leetcode.tree;

import java.util.Arrays;

/**
 * @author luyi
 * @date 2023/12/24 15:55
 */
public class Medium_ConstructMaximumBinaryTree_654 {

    /**
     * 654.最大二叉树
     * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
     * 1、创建一个根节点，其值为 nums 中的最大值。
     * 2、递归地在最大值 左边 的 子数组前缀上 构建左子树。
     * 3、递归地在最大值 右边 的 子数组后缀上 构建右子树。
     * 返回 nums 构建的 最大二叉树 。
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0){
            return null;
        }
        int maxValueIndex = getMaxValueIndex(nums);
        TreeNode root = new TreeNode(nums[maxValueIndex]);
        int leftStart = 0;
        int leftEnd = maxValueIndex;
        int rightStart = maxValueIndex+1;
        int rightEnd = nums.length;
        int[] leftArrays = Arrays.copyOfRange(nums, leftStart, leftEnd);
        int[] rightArrays = Arrays.copyOfRange(nums, rightStart, rightEnd);
        root.left = constructMaximumBinaryTree(leftArrays);
        root.right =  constructMaximumBinaryTree(rightArrays);
        return root;
    }

    private int getMaxValueIndex(int[] nums) {
        int vaule = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0 ; i < nums.length; i++){
            if (nums[i] > vaule){
                index = i;
                vaule = nums[i];
            }
        }
        return index;
    }





    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        return findNode2(nums,0,nums.length);
    }


    public TreeNode findNode2(int[] nums,int start,int end){
        if (end - start < 1){
            // 没有元素了
            return null;
        }
        if (end - start == 1){
            // 只有一个元素
            return new TreeNode(nums[start]);
        }

        int maxValueIndexByRange = getMaxValueIndexByRange(nums, start, end);

        TreeNode root = new TreeNode(nums[maxValueIndexByRange]);
        int leftStart = start;
        int leftEnd = maxValueIndexByRange;
        int rightStart = maxValueIndexByRange+1;
        int rightEnd = end;
        root.left = findNode2(nums,leftStart,leftEnd);
        root.right = findNode2(nums,rightStart,rightEnd);

        return root;
    }

    /**
     * 左闭右开
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int getMaxValueIndexByRange(int[] nums,int start,int end){
        int vaule = Integer.MIN_VALUE;
        int index = -1;
        for (int i = start ; i < end; i++){
            if (nums[i] > vaule){
                index = i;
                vaule = nums[i];
            }
        }
        return index;
    }


    public static void main(String[] args) {
        int [] nums = {3,2,1,6,0,5};
        Medium_ConstructMaximumBinaryTree_654 a= new Medium_ConstructMaximumBinaryTree_654();
        TreeNode node = a.constructMaximumBinaryTree2(nums);


    }




}
