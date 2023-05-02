package com.example.hdlitest.leetcode.tree;

/**
 * @author luyi
 * @date 2023/4/30 4:22 PM
 */
public class MaxDepth_102 {


    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int l  = maxDepth(root.left);
        int r = maxDepth(root.right);
        return Math.max(l,r)+1;
    }






}
