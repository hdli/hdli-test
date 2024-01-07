package com.example.hdlitest.leetcode.tree;

/**
 * @author luyi
 * @date 2024/1/7 18:09
 */
public class Medium_DeleteNode_450 {

    /**
     * 删除二叉搜索树中的节点:给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * 分析情况：
     * 1、没有找到要删除的节点
     * 2、要删除的节点时叶子节点（没有左右节点） 删除该节点
     * 3、左节点不为空，右节点为空             父级节点 setLeft 当前节点的 left
     * 4、左节点为空，右节点不为空             父级节点 setRight 当前级节点的 right
     * 5、左右节点都不为空：
     *      可以选择左 或者 右 当继承人，例如：选右节点当继承人，处理左节点，因为二叉搜索树的特性，右边的值一定都大于左边的，
     *                                      找到右边树的最左节点，将要处理的左树直接放到该节点的左边就好了；
     *                                    当前节点的父节点的右节点 设置为 右树
     *
     * https://programmercarl.com/0450.%E5%88%A0%E9%99%A4%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E8%8A%82%E7%82%B9.html
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            //情况1、
            return null;
        }
        if (root.val == key){
            //情况2、
            if (root.left == null && root.right == null){
                return null;
            }
            //情况3
            else if (root.left != null && root.right == null){
                return root.left;
            }
            //情况4
            else if (root.left == null && root.right != null){
                return root.right;
            }else {
                //情况5:
                //先找右树的最左节点
                TreeNode inherit = root.right;
                while (inherit.left != null){
                    inherit = inherit.left;
                }
                //处理左树
                inherit.left = root.left;
                //处理右树
                return root.right;
            }
        }
        if (key > root.val){
            root.right = deleteNode(root.right,key);
        }else {
            root.left = deleteNode(root.left,key);
        }
        return root;
    }


    /**
     * 迭代法
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode2(TreeNode root, int key){
        if (root == null){
            return null;
        }
        //当前节点
        TreeNode cur = root;
        //父节点
        TreeNode pre = null;
        while (cur != null){
            if (cur.val > key){
                pre = cur;
                cur = cur.left;
            }else if (cur.val < key){
                pre = cur;
                cur = cur.right;
            }else {
                break;
            }
        }
        if (pre == null){
            //头节点 == key
            return deleteOneNode(cur);
        }
        //判断当前节点是左节点
        if (pre.left != null && pre.left.val == key){
            pre.left = deleteOneNode(cur);
        }
        //判断当前节点是右节点
        if (pre.right !=null && pre.right.val == key){
            pre.right = deleteOneNode(cur);
        }
        return root;
    }

    private TreeNode deleteOneNode(TreeNode node){
        if (node == null){
            return null;
        }
        if (node.right == null){
            return node.left;
        }
        if (node.left == null){
            return node.right;
        }
        TreeNode cur = node.right;
        while (cur.left != null){
            cur = cur.left;
        }
        cur.left = node.left;
        return node.right;
    }

}
