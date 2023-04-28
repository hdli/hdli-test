package com.example.hdlitest.leetcode;

/**
 * @author luyi
 * @date 2023/4/26 9:24 PM
 */
public class DiameterOfBinaryTree_543 {


    public static void main(String[] args) {

    }

    public int diameterOfBinaryTree(TreeNode root) {


        return 0;
    }


    static class TreeNode{
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }
}
