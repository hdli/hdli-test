package com.example.hdlitest.leetcode.tree;

/**
 * @author luyi
 * @date 2024/1/7 20:02
 */
public class Medium_TrimBST_669 {

    /**
     * 修剪二叉搜索树:
     * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
     *
     * https://programmercarl.com/0669.%E4%BF%AE%E5%89%AA%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null){
            return null;
        }
        if(root.val < low){
            //当前节点小于最小值了，但是它的右节点比它还大，可能符合范围
            return trimBST(root.right,low,high);
        }
        if(root.val > high){
            //当前节点大于最大值了，但是它的左节点比它还小，可能符合范围
            return trimBST(root.left,low,high);
        }
        root.left = trimBST(root.left,low,high);
        root.right = trimBST(root.right,low,high);
        return root;
    }
}
