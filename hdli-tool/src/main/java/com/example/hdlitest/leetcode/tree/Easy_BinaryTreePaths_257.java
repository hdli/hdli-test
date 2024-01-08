package com.example.hdlitest.leetcode.tree;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归+回溯
 * @author luyi
 * @date 2023/11/19 15:51
 */
public class Easy_BinaryTreePaths_257 {


    private List<String> result = new ArrayList<>();

    /**
     * 二叉树的所有路径：给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null){
            return null;
        }
        handle(root,new ArrayList<>());
        return result;
    }

    /**
     * 递归+回溯
     * @param root
     * @param list
     */
    public void handle(TreeNode root,List<TreeNode> list){
        list.add(root);
        if (root.left == null && root.right == null){
            result.add(getTreeString(list));
            return;
        }
        if (root.left != null){
            handle(root.left,list);
            list.remove(list.size()-1);
        }
        if (root.right != null){
            handle(root.right,list);
            list.remove(list.size()-1);
        }
    }

    private static String getTreeString(List<TreeNode> list) {
        StringBuilder builder = new StringBuilder();
        for (TreeNode key : list) {
            builder.append(key.val).append("->");
        }
        return builder.substring(0,builder.length()-2);
    }
}
