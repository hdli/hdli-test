package com.example.hdlitest.leetcode.tree;

/**
 * 二叉树的最大深度
 * @author luyi
 * @date 2023/4/30 4:22 PM
 */
public class MaxDepth_104 {

    /**
     * 后序遍历：左右中
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int l  = maxDepth(root.left);
        int r = maxDepth(root.right);
        return Math.max(l,r)+1;
    }


    /**
     * 二叉树的最小深度
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量
     * @param root
     * @return
     */
    private int min(TreeNode root){
        if(root == null){
            return 0;
        }
        int ld = min(root.left);
        int rd = min(root.right);

        //最小深度是从根节点到最近叶子节点的最短路径上的节点数量
        if(root.left == null && root.right != null){
            return 1+rd;
        }
        if(root.left != null && root.right == null){
            return 1+ld;
        }
        return 1+Math.min(ld,rd);
    }






}
