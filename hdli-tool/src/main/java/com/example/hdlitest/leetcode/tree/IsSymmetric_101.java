package com.example.hdlitest.leetcode.tree;

/**
 * @author luyi
 * @date 2023/4/28 7:36 PM
 */
public class IsSymmetric_101 {

    /**
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。对称二叉树
     *
     * 以根节点为中轴，判断左右两颗子树，是否可以翻转
     *
     *                    1(根)
     *                    |   \
     *           2(外)             2(外)
     *          |    \            |     \
     *      3(外)     4(内)    4(内)     3(外)
     *      |    \            |   \
     *   5(外)  6(内)       6(内) 5(外)
     *
     *   左子树的左节点是外，  右子树的右节点是外
     *
     *  排序顺序：只能使用 后序遍历（左右中），因为需要先判断左右节点，左右阶段的判断结果影响中节点；
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        return compare(root.left,root.right);

    }

    private boolean compare(TreeNode left,TreeNode right){
        if (left != null && right == null){
            return false;
        }
        if (left == null && right != null){
            return false;
        }
        if (left == null && right == null){
            return true;
        }
        if (left.val != right.val){
            return false;
        }
        //排序顺序：只能使用 后序遍历（左右中），因为需要先判断左右节点，左右阶段的判断结果影响中节点；

        //外侧 对比  （左子树的：左。右子树的：右）
        boolean outside = compare(left.left, right.right);
        //内侧 对比  （左子树的：右。右子树的：左）
        boolean inside = compare(left.right, right.left);
        //中间 对比  （左右节点的结果，得到中节点。只有左右都相同中节点才相同）
        boolean middle = (outside && inside);

        return middle;
    }
}
