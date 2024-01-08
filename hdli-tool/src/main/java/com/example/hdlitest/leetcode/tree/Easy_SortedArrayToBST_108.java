package com.example.hdlitest.leetcode.tree;

/**
 *
 * 数据转二叉树，左右边界
 * @author luyi
 * @date 2024/1/8 22:40
 */
public class Easy_SortedArrayToBST_108 {
    /**
     * 将有序数组转换为二叉搜索树:
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     *
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     *
     * 思路：
     * 有序数组，就是二叉搜索树 中序遍历的结果，所以取数据的中间就是根节点，剩下左侧的数组按同样的思维找中间就是左节点，右侧找右节点
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return recursion(nums,0,nums.length - 1);
    }

    /**
     * 左闭右闭区间[left, right]
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private TreeNode recursion(int[] nums,int left, int right){
        if (left > right){
            return null;
        }
        int middle = (left + right)/2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = recursion(nums,left,middle-1);
        root.right = recursion(nums,middle+1,right);
        return root;
    }

}
