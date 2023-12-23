package com.example.hdlitest.leetcode.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luyi
 * @date 2023/12/23 17:24
 */
public class Medium_BuildTree_106 {


    /**
     * 从中序与后序遍历序列构造二叉树
     * @param inorder   中序遍历   左中右
     * @param postorder 后序遍历   左右中     最后一个是根节点
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0){
            return null;
        }
        int rootValue = postorder[postorder.length-1];
        TreeNode rootNode = new TreeNode(rootValue);
        int rootInorderIndex = getValueIndex(inorder, rootValue);
        //数组切割遵从左闭右开原则
        int leftInorderStart = 0;
        int leftInorderEnd = rootInorderIndex;
        int rightInorderStart = rootInorderIndex + 1;
        int rightInorderEnd = inorder.length;
        int[] leftInorder = Arrays.copyOfRange(inorder, leftInorderStart, leftInorderEnd);
        int[] rightInorder = Arrays.copyOfRange(inorder, rightInorderStart, rightInorderEnd);

        int leftPostorderStart = 0;
        int leftPostorderEnd = leftInorder.length;
        int rightPostorderStart = leftPostorderEnd;
        int rightPostorderEnd = postorder.length - 1;
        int[] leftPostorder = Arrays.copyOfRange(postorder, leftPostorderStart, leftPostorderEnd);
        int[] rightPostorder = Arrays.copyOfRange(postorder, rightPostorderStart, rightPostorderEnd);

        rootNode.left = buildTree(leftInorder,leftPostorder);
        rootNode.right = buildTree(rightInorder,rightPostorder);
        return rootNode;
    }




    Map<Integer, Integer> map;  // 方便根据数值查找位置
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) { // 用map保存中序序列的数值对应位置
            map.put(inorder[i], i);
        }

        return findNode(inorder,  0, inorder.length, postorder,0, postorder.length);  // 前闭后开
    }

    /**
     * 这个方法，使用九俩个数据中寻找，不用生成那么多数组对象
     * @param inorder
     * @param inBegin
     * @param inEnd
     * @param postorder
     * @param postBegin
     * @param postEnd
     * @return
     */
    public TreeNode findNode(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd) {
        // 参数里的范围都是前闭后开
        if (inBegin >= inEnd || postBegin >= postEnd) {  // 不满足左闭右开，说明没有元素，返回空树
            return null;
        }
        //不用每次递归都遍历中序数组
        int rootIndex = map.get(postorder[postEnd - 1]);  // 找到后序遍历的最后一个元素在中序遍历中的位置
        TreeNode root = new TreeNode(inorder[rootIndex]);  // 构造结点
        int lenOfLeft = rootIndex - inBegin;  // 保存中序左子树个数，用来确定后序数列的个数
        root.left = findNode(inorder, inBegin, rootIndex,
                postorder, postBegin, postBegin + lenOfLeft);
        root.right = findNode(inorder, rootIndex + 1, inEnd,
                postorder, postBegin + lenOfLeft, postEnd - 1);

        return root;
    }





    private int getValueIndex(int [] s,int value){
        for (int i = 0; i < s.length; i++) {
            if (value == s[i]){
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int [] inorder = {9,3,15,20,7};
        int [] postorder = {9,15,7,20,3};
        Medium_BuildTree_106 a = new Medium_BuildTree_106();
        TreeNode node = a.buildTree(inorder, postorder);
        node.print2();
    }

}
