package com.example.hdlitest.leetcode.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author luyi
 * @date 2024/7/2 00:36
 */
public class Medium_AllPathsSourceTarget_797 {


    List<List<Integer>> result = new ArrayList<>();

    LinkedList<Integer> path = new LinkedList<>();


    /**
     * 所有可能路径：
     *
     * dfs
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        path.add(0);
        dfs(graph,0);
        path.clear();
        return result;
    }

    private void dfs(int[][] graph,int direction){
        if(direction == (graph.length -1)){
            result.add(new ArrayList(path));
            return;
        }
        for (int i = 0; i < graph[direction].length; i++) {
            path.add(graph[direction][i]);
            dfs(graph,graph[direction][i]);
            path.removeLast();
        }
    }
}
