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
 * -----------------二叉树的种类
 * 满二叉树：如果一棵二叉树只有度为0的结点和度为2的结点，并且度为0的结点在同一层上，则这棵二叉树为满二叉树。
 * 完全二叉树：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层（h从1开始），则该层包含 1~ 2^(h-1) 个节点。
 * 二叉搜索树：二叉搜索树（BST）是一个有序树，左子树上所有结点的值均小于它的根结点的值，右子树上所有结点的值均大于它的根结点的值
 * (二叉搜索树 中序遍历 有序数组 左中右 )
 * 平衡二叉搜索树：又被称为AVL（Adelson-Velsky and Landis）树，且具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 *
 * -----------------二叉树存储方式
 * 1、链式存储（链表 左右指针）
 * 2、顺序存储（数组）如果父节点的数组下标是 i，那么它的左孩子就是 i * 2 + 1，右孩子就是 i * 2 + 2
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

    public void print2(){
        print2(this);
    }
    public void print2 (TreeNode node) {
        List<Integer> integers = TreeNode.qianXu(node);
        for (Integer integer : integers) {
            if (integer == null){
                System.out.print("null,");
            }else {
                System.out.print(integer+",");
            }
        }
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);

        node20.left = node15;
        node20.right = node7;

        node3.left = node9;
        node3.right = node20;

        List<Integer> integers2 = qianXu(node3);
        System.out.print("前序数组:");
        integers2.forEach((i)->{
            System.out.print(i+",");
        });
        System.out.println();
        List<Integer> integers = zhongXu(node3);
        System.out.print("中序数组:");
        integers.forEach((i)->{
            System.out.print(i+",");
        });
        System.out.println();
        List<Integer> integers1 = houXu(node3);
        System.out.print("后序数组:");
        integers1.forEach((i)->{
            System.out.print(i+",");
        });
    }

    /**
     * 二叉树转 前序数组
     * @param node
     * @return
     */
    public static List<Integer> qianXu(TreeNode node){
        List<Integer> result = new ArrayList<>();
        qianRecursion(node,result);
        return result;
    }

    private static void qianRecursion(TreeNode node,List<Integer> result){
        if (node == null){
            return;
        }
        //中
        result.add(node.val);
        //左
        qianRecursion(node.left,result);

        //右
        qianRecursion(node.right,result);

    }

    /**
     * 二叉树转 中序数组
     * @param node
     * @return
     */
    public static List<Integer> zhongXu(TreeNode node){
        List<Integer> result = new ArrayList<>();
        zhongRecursion(node,result);
        return result;
    }

    private static void zhongRecursion(TreeNode node,List<Integer> result){
        if (node == null){
            return;
        }
        //左
        if (node.left != null){
            zhongRecursion(node.left,result);
        }
        //中
        result.add(node.val);
        //右
        if (node.right != null){
            zhongRecursion(node.right,result);
        }
    }

    /**
     * 二叉树转 后序数组
     * @param node
     * @return
     */
    public static List<Integer> houXu(TreeNode node){
        List<Integer> result = new ArrayList<>();
        houRecursion(node,result);
        return result;
    }

    private static void houRecursion(TreeNode node,List<Integer> result){
        if (node == null){
            return;
        }
        //左
        if (node.left != null){
            houRecursion(node.left,result);
        }
        //右
        if (node.right != null){
            houRecursion(node.right,result);
        }
        //中
        result.add(node.val);
    }

}
