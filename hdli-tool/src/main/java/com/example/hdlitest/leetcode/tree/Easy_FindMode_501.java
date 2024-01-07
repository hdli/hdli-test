package com.example.hdlitest.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luyi
 * @date 2024/1/6 20:17
 */
public class Easy_FindMode_501 {

    /**
     * 二叉搜索树中的众数：
     * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
     *
     * 如果树中有不止一个众数，可以按 任意顺序 返回。
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        test(root);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }


    /**
     * 仅遍历一次
     */
    //最高频率
    int maxCount = 0;
    //每个数 统计
    int count = 0;
    TreeNode pre = null;

    List<Integer> result = new ArrayList<>();
    private void test(TreeNode root){
        if(root == null){
            return;
        }
        test(root.left);
        if (pre == null){
            count =1;
        } else if (pre.val == root.val) {
            count++;
        }else {
            count=1;
        }
        pre = root;
        if (count == maxCount){
            result.add(root.val);
        }else if (count > maxCount){
            maxCount = count;
            result.clear();
            result.add(root.val);
        }

        test(root.right);
    }


    /**
     * 暴力解法:先用map遍历二叉树，收集每个数据出现的频率，再排序,再从数据中找出前面几个相同最高频率的
     * @param root
     * @return
     */
    public int[] findMode2(TreeNode root){
        if (root == null){
            return new int[0];
        }
        List<Integer> result = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        traversal(root,map);
        List<Map.Entry<Integer, Integer>> collect = map.entrySet().stream().sorted((a, b) -> b.getValue().compareTo(a.getValue())).collect(Collectors.toList());
        result.add(collect.get(0).getKey());
        for (int i = 1;i < collect.size(); i++){
            if (collect.get(i).getValue().equals(collect.get(i-1).getValue())){
                result.add(collect.get(i).getKey());
            }else {
                //第一次不等必须中断，后序
                break;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void traversal(TreeNode root,Map<Integer,Integer> map){
        if (root == null){
            return;
        }
        map.put(root.val,map.getOrDefault(root.val,0)+1);
        traversal(root.left,map);
        traversal(root.right,map);
    }




}
