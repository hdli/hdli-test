package com.example.hdlitest.leetcode.tree;

/**
 * @author luyi
 * @date 2024/1/7 20:31
 */
public class Easy_CountNodes_222 {

    /**
     * 完全二叉树的节点个数
     * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层（h从1开始），则该层包含 1~ 2^(h-1) 个节点。
     *
     * 先判断子树是否是满二叉树
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
     * 判断是不是 满二叉树
     * 满二叉树 数量使用公司  2的深度次方-1
     * @param node
     * @return
     */
    public int checkFullTree(TreeNode node){
        int leftDepth = 1;int rightDepth = 1;
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        while (leftNode != null){
            leftDepth++;
            leftNode = leftNode.left;
        }
        while (rightNode != null){
            rightDepth++;
            rightNode = rightNode.right;
        }
        if (leftDepth == rightDepth){
            return (int)Math.pow(2,leftDepth)-1;
        }
        return -1;
    }
}
