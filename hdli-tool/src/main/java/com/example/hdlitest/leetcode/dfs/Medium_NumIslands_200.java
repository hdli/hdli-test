package com.example.hdlitest.leetcode.dfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author luyi
 * @date 2024/7/3 00:21
 */
public class Medium_NumIslands_200 {

    /**
     * 岛屿数量
     * 为了统计岛屿数量同时不重复记录，每当我们搜索到一个岛后，就将这个岛 “淹没” —— 将这个岛所占的地方从 “1” 改为 “0”，
     * 这样就不用担心后续会重复记录这个岛屿了。而 DFS 的过程就体现在 “淹没” 这一步中。
     * @param grid
     * @return
     */
    public int numIslandsDfs(char[][] grid) {
        //记录找到的岛屿数量
        int result = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                //找到“1”，result加一，同时淹没这个岛
                if (grid[i][j] == '1'){
                    result++;
                    dfs(grid,i,j);
                }
            }
        }
        return result;
    }

    /**
     * 使用DFS“淹没”岛屿
     * @param grid
     * @param i
     * @param j
     */
    public void dfs(char[][] grid,int i,int j){
        //搜索边界：索引越界或遍历到了"0"
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0'){
            return;
        }
        //将这块土地标记为"0"
        grid[i][j] = '0';
        //根据"每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成"，对上下左右的相邻顶点进行dfs
        //向右走
        dfs(grid,i+1,j);
        //向左走
        dfs(grid,i-1,j);
        //向下走
        dfs(grid,i,j+1);
        //向上走
        dfs(grid,i,j-1);
    }



    public int numIslandsBfs(char[][] grid) {
        int result = 0;
        //标记走过的坐标  visited[i][j] = true 代表走过了
        boolean [][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                //找到“1”，result加一，同时淹没这个岛
                if (!visited[i][j] && grid[i][j] == '1'){
                    result++;
                    bfs(grid,visited,i,j);
                }
            }
        }
        return result;
    }

    public void bfs(char[][] grid,boolean [][] visited,int y,int x){
        Deque<int []> queue = new ArrayDeque<>();
        queue.add(new int[]{y,x});
        visited[y][x] = true;
        while (!queue.isEmpty()){
            int [] point = queue.poll();
            int i = point[0];
            int j = point[1];
            //向下走
            if (j+1 < grid[0].length && !visited[i][j+1] && grid[i][j+1] == '1'){
                queue.add(new int[]{i,j+1});
                //只要加入队列就标记为访问
                visited[i][j+1] = true;
            }
            //向上走
            if (j-1 >= 0 && !visited[i][j-1] && grid[i][j-1] == '1'){
                queue.add(new int[]{i,j-1});
                visited[i][j-1] = true;
            }
            //向右走
            if (i+1 < grid.length && !visited[i+1][j] && grid[i+1][j] == '1'){
                queue.add(new int[]{i+1,j});
                visited[i+1][j] = true;
            }
            //向左走
            if (i-1 >= 0 && !visited[i-1][j] && grid[i-1][j] == '1'){
                queue.add(new int[]{i-1,j});
                visited[i-1][j] = true;
            }
        }
    }



}
