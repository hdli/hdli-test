package com.example.hdlitest.leetcode.tree;

/**
 * @author luyi
 * @date 2023/4/30 4:39 PM
 */
public class MergeTrees_617 {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        return t(root1,root2);

    }


    private static TreeNode t(TreeNode root1, TreeNode root2){
        if (root1 == null){
            return root2;
        }
        if (root2 == null){
            return root1;
        }
        TreeNode node = new TreeNode();
        node.val = root1.val + root2.val;
        node.left = t(root1.left,root2.left);
        node.right = t(root1.right,root2.right);
        return node;
    }
}
