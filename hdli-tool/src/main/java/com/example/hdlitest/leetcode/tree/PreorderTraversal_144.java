package com.example.hdlitest.leetcode.tree;

import java.util.*;

/**
 * @author luyi
 * @date 2023/4/28 12:33 AM
 */
public class PreorderTraversal_144 {

    /**
     * 二叉树的前序遍历： 中左右
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursion(root,result);
        return result;
    }

    //递归遍历
    public void recursion(TreeNode root,List<Integer> result){
        if (root == null){
            return;
        }
        //中
        result.add(root.val);
        //左
        recursion(root.left,result);
        //右
        recursion(root.right,result);
    }

    /**
     * 非递归遍历：迭代遍历 前序遍历：中左右
     * @param root
     * @param result
     */
    public void iteration(TreeNode root,List<Integer> result){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            result.add(pop.val);
            if (pop.right != null){
                stack.push(pop.right);
            }
            if (pop.left != null){
                stack.push(pop.left);
            }
        }
    }

    /**
     * 迭代排序：中序遍历：左中右
     * @param root
     * @param result
     */
    public void iteration2(TreeNode root,List<Integer> result){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        //定一个指针
        TreeNode currentNode = root;
        while (currentNode != null || !stack.isEmpty()){
            if (currentNode != null){
                stack.add(currentNode);
                currentNode = currentNode.left;
            }else {
                currentNode = stack.pop();
                result.add(currentNode.val);
                currentNode = currentNode.right;
            }
        }
    }
}
