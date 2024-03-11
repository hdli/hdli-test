package com.example.hdlitest.leetcode.tree;

/**
 * 二叉树的最大深度:
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
     * 前序遍历 + 回溯
     */
    int maxDepth = 0;
    public void qianxu(TreeNode node,int depth){
        if (node.left == null && node.right == null){
            maxDepth = Math.max(maxDepth,depth);
            return;
        }
        if (node.left != null){
            qianxu(node.left,depth+1);
        }
        if (node.right != null){
            qianxu(node.right,depth+1);
        }
    }







    /**
     * 二叉树的最小深度
     * 后序遍历
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量
     * @param root
     * @return
     */
    private int min(TreeNode root){
        if(root == null){
            return 0;
        }
        //后序遍历
        int ld = min(root.left);
        int rd = min(root.right);

        //处理特殊情况
        if(root.left == null && root.right != null){
            return 1+rd;
        }
        if(root.left != null && root.right == null){
            return 1+ld;
        }
        return 1+Math.min(ld,rd);
    }


    int minDepth = Integer.MAX_VALUE;
    /**
     * 二叉树的最小深度 ： 前序遍历 + 回溯
     * @param node
     * @return
     */
    public void minDepth(TreeNode node,int depth) {
        if (node.left == null && node.right == null){
            minDepth = Math.min(minDepth,depth);
            return;
        }
        if (node.left != null){
            minDepth(node.left,depth+1);
        }
        if (node.right != null){
            minDepth(node.right,depth+1);
        }

    }






}
