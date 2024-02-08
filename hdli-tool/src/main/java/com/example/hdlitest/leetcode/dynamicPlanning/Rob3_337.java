package com.example.hdlitest.leetcode.dynamicPlanning;

import com.example.hdlitest.leetcode.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author luyi
 * @date 2024/2/8 22:25
 */
public class Rob3_337 {

    /**
     * 打家劫舍 III
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
     *
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     *
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     *
     *
     * 状态标记递归
     *
     * 不偷：Max(左孩子不偷，左孩子偷) + Max(右孩子不偷，右孩子偷)
     * 偷：左孩子不偷+ 右孩子不偷 + 当前节点偷
     *
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] dp = traverse(root);
        return Math.max(dp[0],dp[1]);
    }

    //后序遍历，左右中
    //dp[0],代表不偷当前节点，偷取的最大钱财
    //dp[1],代表偷当前节点，偷取的最大钱财
    public int [] traverse(TreeNode root){
        if (root == null){
            return new int[] {
                0,0
            };
        }
        //左
        int [] leftDp = traverse(root.left);
        //右
        int [] rightDp = traverse(root.right);

        //中

        //如果当前节点偷
        int fetch = root.val + leftDp[0]+ rightDp[0];
        //如果当前节点不偷
        int letOff = Math.max(leftDp[0],leftDp[1])+Math.max(rightDp[0],rightDp[1]);
        return new int[]{letOff,fetch};
    }




}
