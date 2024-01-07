package com.example.hdlitest.leetcode.tree;

/**
 * @author luyi
 * @date 2024/1/7 16:27
 */
public class Medium_LowestCommonAncestor_236 {

    /**
     * 二叉树的最近公共祖先:
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 由下向上判断，后序遍历：左右中
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //终止条件
        if(root == null){
            return null;
        }
        if(p == root || q == root){
            return root;
        }
        //左
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        //右
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        //中
        if(left != null && right != null){
            return root;
        }
        if(left == null && right != null){
            return right;
        }
        if(left != null && right == null){
            return left;
        }
        return null;
    }
}
