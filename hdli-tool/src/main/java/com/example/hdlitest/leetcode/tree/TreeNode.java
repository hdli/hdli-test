package com.example.hdlitest.leetcode.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树 的递归遍历
 *  前序遍历：中左右
 *  中序遍历：左中右
 *  后序遍历：左右中
 *
 *  二叉树的BFS和DFS指的是对于二叉树的遍历方式。
 *
 * BFS(广度优先搜索)是一种层次遍历算法，从根节点开始，按照从上到下、从左到右的顺序依次访问每一个节点，直到遍历完整棵树。
 *
 * DFS(深度优先搜索)是一种优先访问深度节点的算法，它分为先序遍历、中序遍历和后序遍历。先序遍历是从根节点开始，按照左子树、右子树的顺序依次访问每一个节点；��序遍历是从左子树开始，按照根节点、右子树的顺序依次访问每一个节点；后序遍历是从左子树、右子树开始，按照根节点的顺序依次访问每一个节点。
 *
 * BFS和DFS适用于不同的场景。BFS适合解决最短路径问题，DFS适合解决搜索问题。在实际应用中，需要根据问题的具体特点来选择合适的遍历方式。
 *
 *
 *
 * 二叉树的高度:二叉树中任意一个节点到叶子节点的距离   后续遍历 ：左右中   从下往上计数
 * 二叉树的深度:二叉树中任意一个节点到根节点的距离     前序遍历 ：中左右   从上往下计数
 *
 *
 * @author luyi
 * @date 2023/4/27 11:47 PM
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }


    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public static TreeNode asBfs (Integer ... nums) {
        TreeNode root = new TreeNode(nums[0]);
        List<Integer> list = new ArrayList<>(nums.length-1);
        for (int i = 1; i < nums.length; i++) {
            list.add(nums[i]);
        }
        Iterator<Integer> iterator = list.iterator();
        List<TreeNode> rootList = new ArrayList<>();
        rootList.add(root);
        bfs(rootList, new ArrayList<>(), iterator);
        return root;
    }

    public static void bfs (List<TreeNode> parentTreeNodeList, List<TreeNode> currentTreeNodeList, Iterator<Integer> iterator) {
        parentTreeNodeList.stream().forEach(treeNode -> {
            if (iterator.hasNext()) {
                Integer nextVal = iterator.next();
                if (nextVal == null) {
                    currentTreeNodeList.add(null);
                } else {
                    treeNode.left = new TreeNode(nextVal);
                    currentTreeNodeList.add(treeNode.left);
                }
            }

            if (iterator.hasNext()) {
                Integer nextVal = iterator.next();
                if (nextVal == null) {
                    currentTreeNodeList.add(null);
                } else {
                    treeNode.right = new TreeNode(nextVal);
                    currentTreeNodeList.add(treeNode.right);
                }
            }
        });
        if (iterator.hasNext()) {
            bfs(currentTreeNodeList, new ArrayList<>(), iterator);
        }
    }


    public void bfs (Queue<TreeNode> queue, List<List<Integer>> results) {
        if (queue.isEmpty()) {
            return;
        }
        List<Integer> curLevel = new ArrayList<>();
        int n = queue.size();
        for (int i = 0; i < n; i++) {
            TreeNode node = queue.poll();
            curLevel.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        results.add(curLevel);
        bfs(queue, results);
    }


    public void print () {
        print(this);
    }

    public void print (TreeNode node) {
        boolean isNull = false;
        if (node.left != null) {
            print(node.left);
        } else {
            isNull = true;
        }
        if (node.right != null) {
            print(node.right);
        } else {
            isNull = true;
        }
        if (isNull) {
            System.out.print("null" + ", ");
        } else {
            System.out.print(node.val + ", ");
        }
    }

}
