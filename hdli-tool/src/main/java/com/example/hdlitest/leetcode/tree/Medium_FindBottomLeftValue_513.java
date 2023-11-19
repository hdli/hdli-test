package com.example.hdlitest.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author luyi
 * @date 2023/11/19 17:54
 */
public class Medium_FindBottomLeftValue_513 {

    /**
     * 找树左下角的值:给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
     *              假设二叉树中至少有一个节点。
     *
     * 迭代法：获取二叉树最后一层的第一个节点值
     *
     * 递归法：获取深度最深的最左边的那个节点（这个节点可能是左节点也可能是右节点）
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size;i++){
                TreeNode node = queue.poll();
                if (i == 0){
                    result = node.val;;
                }
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return result;
    }

    /**
     * 递归法
     */

    private int Deep = -1;
    private int value = 0;
    public int findBottomLeftValue2(TreeNode root){
        value = root.val;
        recursion(root,1);
        return value;
    }

    private void recursion(TreeNode node,int depth){
        if (node == null){
            return;
        }
        if (node.left == null && node.right == null){
            if (depth > Deep){
                value = node.val;
                Deep = depth;
            }
        }
        if (node.left != null){
            depth++;
            recursion(node.left,depth);
            depth--;
            // 隐藏着回溯
            //recursion(node.left,depth+1);
        }
        if (node.right != null){
            depth++;
            recursion(node.right,depth);
            depth--;
        }
    }
}
