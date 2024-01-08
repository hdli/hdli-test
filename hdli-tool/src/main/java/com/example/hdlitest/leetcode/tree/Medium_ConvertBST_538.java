package com.example.hdlitest.leetcode.tree;

/**
 * 双节点遍历， 右中左
 * @author luyi
 * @date 2024/1/8 23:34
 */
public class Medium_ConvertBST_538 {

    /**
     * 把二叉搜索树转换为累加树
     *
     *
     */
    TreeNode pre = null;
    public TreeNode convertBST(TreeNode root) {
        if(root == null){
            return null;
        }
        //右
        root.right = convertBST(root.right);
        //中
        if(pre != null){
            root.val = pre.val+root.val;
        }
        pre = root;
        //左
        root.left = convertBST(root.left);
        return root;
    }
}
