package com.example.hdlitest.leetcode.tree;

/**
 * @author luyi
 * @date 2024/1/7 19:36
 */
public class Medium_InsertIntoBST_701 {

    /**
     * 二叉搜索树中的插入操作:
     * 最简单的思路是不改变结构，把要插入的数放到叶子节点上
     * https://programmercarl.com/0701.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E6%8F%92%E5%85%A5%E6%93%8D%E4%BD%9C.html
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            //到叶子节点了
            return new TreeNode(val);
        }
        if (val > root.val){
            root.right = insertIntoBST(root.right,val);
        }
        if (val < root.val){
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
}
