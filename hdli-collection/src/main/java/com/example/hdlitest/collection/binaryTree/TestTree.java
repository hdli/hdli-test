package com.example.hdlitest.collection.binaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author luyi
 * @date 2023/4/16 8:41 PM
 */
public class TestTree {


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> nodeValList = new ArrayList<>();
        if (root == null) return nodeValList;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        bfs(queue, nodeValList);
        return nodeValList;
    }

    public void bfs (Queue<TreeNode> queue, List<List<Integer>> nodeValList) {
        // 退出条件
        if (queue.isEmpty()) {
            return;
        }

        // 当前深度的元素集合
        List<Integer> curDepthValList = new ArrayList<>();
        nodeValList.add(curDepthValList);
        int len = queue.size();
        for (int i = 0; i < len; i++) {
            TreeNode treeNode = queue.poll();
            if (treeNode != null) {
                curDepthValList.add(treeNode.val);
                if (treeNode.left != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }
        }
        bfs(queue, nodeValList);
    }
}
