package com.example.hdlitest.leetcode.tree;

/**
 *
 * 完全二叉树的节点个数
 * @author luyi
 * @date 2023/10/23 21:06
 */
public class CountNodes_222 {
    /**
     * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
     * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        int i = checkFullTree(root);
        if (i != -1){
            return i;
        }
        int leftNums = countNodes(root.left);
        int rightNums = countNodes(root.right);
        return leftNums+rightNums+1;
    }

    /**
     * 利用完全二叉树的特性
     * @param node
     * @return
     */
    public int checkFullTree(TreeNode node){
        int leftDepth = 1;int rightDepth = 1;
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        while (leftNode != null){
            leftNode = leftNode.left;
            leftDepth++;
        }
        while (rightNode != null){
            rightNode = rightNode.right;
            rightDepth++;
        }
        if (leftDepth == rightDepth){
            //2的次方 - 1
            return (int)Math.pow(2,leftDepth)-1;
        }
        return -1;
    }

}
