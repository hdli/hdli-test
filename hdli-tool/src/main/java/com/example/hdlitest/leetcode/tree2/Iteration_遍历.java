package com.example.hdlitest.leetcode.tree2;

import com.example.hdlitest.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 深度优先
 * 迭代法遍历
 * @author luyi
 * @date 2024/3/4 23:59
 */
public class Iteration_遍历 {

    /**
     * 前序遍历:中左右
     * @param root
     */
    public List<Integer> qianxu(TreeNode root){
        if (root == null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> result = new ArrayList<>();
        while(!stack.isEmpty()){
            TreeNode pop = stack.pop();
            result.add(pop.val);
            if (pop.right != null){
                stack.push(pop.right);
            }
            if (pop.left != null){
                stack.push(pop.left);
            }
        }
        return result;
    }


    /**
     * 中序遍历：左中右
     * @param root
     */
    public List<Integer> zhongxu(TreeNode root){
        if (root == null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null){
            if (cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }

    /**
     * 后序遍历：左右中
     * 反转前序遍历的结果就可以了
     * @param root
     * @return
     */
    public List<Integer> houxu(TreeNode root){
        List<Integer> qianxu = qianxu(root);
        Collections.reverse(qianxu);
        return qianxu;
    }
}
