package com.example.hdlitest.leetcode.tree;

/**
 *
 * 平衡二叉树：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * @author luyi
 * @date 2023/11/11 16:09
 */
public class Easy_IsBalanced_110 {



    public boolean isBalanced(TreeNode root) {
        int l = getHeight(root);
        if (l == -1){
            return false;
        }
        return true;
    }


    public int getHeight(TreeNode node){
        if (node == null){
            return 0;
        }
        int l = getHeight(node.left);
        if (l == -1){
            return -1;
        }
        int r = getHeight(node.right);
        if (r == -1){
            return -1;
        }
        int result = Math.abs(l-r);
        if (result > 1){
            return -1;
        }
        return Math.max(l,r)+1;
    }



}
