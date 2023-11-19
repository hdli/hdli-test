package com.example.hdlitest.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyi
 * @date 2023/11/19 17:14
 */
public class Easy_SumOfLeftLeaves_404 {



    /**
     * 左叶子之和
     * 如何获取左叶子，判断当前节点是左节点（只能由父级节点判断），还是叶子节点（左右节点都为null）
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        handle(root,list);
        if (list.isEmpty()){
            return 0;
        }
        int result = 0;
        for (TreeNode temp : list) {
            result+=temp.val;
        }
        return result;
    }

    private void handle(TreeNode node, List<TreeNode> list){
        if (node == null){
            return;
        }
        if (node.left == null && node.right == null){
            //因为收集左页子节点，我们是通过叶子节点的父级来获取的，所有叶子节点就不需要处理，
            //该判断也只是少走一层判断（可忽视）
            return;
        }
        if (node.left != null && node.left.left == null && node.left.right == null){
            list.add(node.left);
        }
        handle(node.left,list);
        handle(node.right,list);
    }

}
