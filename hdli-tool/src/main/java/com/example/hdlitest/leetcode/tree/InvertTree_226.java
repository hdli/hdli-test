package com.example.hdlitest.leetcode.tree;

/**
 * @author luyi
 * @date 2023/4/28 4:30 PM
 */
public class InvertTree_226 {


    /**
     *
     * 反转二叉树
     * 自己解出来的
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    private static void invert(TreeNode root){
        if (root == null){
            return;
        }
        TreeNode cur = root.right;
        root.right = root.left;
        root.left = cur;
        invert(root.left);
        invert(root.right);
    }
}
