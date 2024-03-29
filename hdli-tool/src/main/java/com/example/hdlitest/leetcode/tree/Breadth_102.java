package com.example.hdlitest.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的迭代遍历
 * @author luyi
 * @date 2023/4/28 4:21 PM
 */
public class Breadth_102 {

    /**
     *
     * BFS
     * 层序遍历，广度优先搜索 102
     * 考虑：操作当前层，还需要保留下一层
     * 一开始想的是使用两个list，一个是当前层数据，一个是下一层，遍历当前层，生产下一层
     * 可以使用队列把两个list，放到一个队列中，控制好每层取的数就好
     * @param root
     * @param result
     */
    public void breadth(TreeNode root,List<List<Integer>> result){
        if (root == null){
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            //每层需要取的节点个数
            int size = queue.size();
            List<Integer> hierarchyResult = new ArrayList<>();
            while (size-- > 0) {
                TreeNode poll = queue.poll();
                hierarchyResult.add(poll.val);
                if (poll.left != null){
                    queue.add(poll.left);
                }
                if (poll.right != null){
                    queue.add(poll.right);
                }
            }
            result.add(hierarchyResult);
        }
    }


    public List<List<Integer>> resList = new ArrayList<List<Integer>>();

    /**
     * DFS
     * @param node
     * @param deep
     */
    public void checkFun01(TreeNode node,int deep){
        if (node == null){
            return;
        }
        deep++;
        if (resList.size() < deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<Integer>();
            resList.add(item);
        }
        resList.get(deep - 1).add(node.val);

        checkFun01(node.left, deep);
        checkFun01(node.right, deep);
    }
}
