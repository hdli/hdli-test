package com.example.hdlitest.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyi
 * @date 2023/12/24 18:24
 */
public class Medium_IsValidBST_98 {

    /**
     * 验证二叉搜索树
     *  * 二叉搜索树：二叉搜索树（BST）是一个有序树，左子树上所有结点的值均小于它的根结点的值，右子树上所有结点的值均大于它的根结点的值
     *  * (二叉搜索树 中序遍历 有序(升序)数组 左中右 )
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        List<Integer> list = new ArrayList<>();
        getList(root,list);
        return isASC(list);
    }

    private void getList(TreeNode root,List<Integer> list){
        if(root == null){
            return;
        }
        //左
        getList(root.left,list);
        //中
        list.add(root.val);
        //右
        getList(root.right,list);
    }

    private boolean isASC(List<Integer> list){
        for(int i = 0;i < list.size()-1;i++){
            if(list.get(i) >= list.get(i+1)){
                return false;
            }
        }
        return true;
    }


    /**
     * 中序遍历：有序递增
     * 判断如果有上一个节点的值大于当前节点，那就不是二叉搜索树
     */
    //记录上一个节点
    TreeNode preNode = null;

    public boolean isValidBST2(TreeNode currentNode) {
        if(currentNode == null){
            return true;
        }
        isValidBST2(currentNode.left);
        if (preNode != null && preNode.val > currentNode.val){
            return false;
        }
        preNode = currentNode;
        isValidBST2(currentNode.right);
        return true;
    }
}
