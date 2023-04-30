package com.example.hdlitest.leetcode.tree;

/**
 * @author luyi
 * @date 2023/4/28 8:21 PM
 */
public class DiameterOfBinaryTree_543 {
    int max = 0;

    /**
     * 遍历每一个节点，以每一个节点为中心点计算最长路径（左子树边长+右子树边长），更新全局变量max。
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0;
        }
        jisuan(root);
        return max;
    }


    private int jisuan(TreeNode node){
        if (node.left == null && node.right == null){
            return 0;
        }
        int leftSize = node.left == null ? 0 : jisuan(node.left)+1;

        int rightSize = node.right == null ? 0 : jisuan(node.right)+1;
        max = Math.max(max,leftSize+rightSize);
        return Math.max(leftSize,rightSize);
    }



}
