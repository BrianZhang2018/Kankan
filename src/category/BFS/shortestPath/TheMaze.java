package category.BFS.shortestPath;

import java.util.*;
/**
 * https://leetcode.com/problems/the-maze/description/
 * https://www.lintcode.com/problem/the-maze
 *
 * BFS找最短路径(其实是steps), so we can skip the visited node at that level as we need know how much steps reach the target
 * rather than how many path to the target (dfs + backtracking)
 * Here, mm and nn refers to the number of rows and columns of the maze.
 *
 * Space complexity : O(mn). visited array of size m*n is used and queue size can grow upto m*n in worst case.
 * reference：/Users/brianzhang/workspace/WaWahaha/src/DFS vs BFS
 *
 * Created by brianzhang on 4/9/19.
 */
public class TheMaze {
    public static void main(String[] args) {
        int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        System.out.println(hasPath(maze, new int[]{0,4}, new int[]{4,4}));
    }

    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || start == null || start.length == 0 || destination == null || destination.length == 0) {
            return false;
        }
        int[][] dicts = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            if (r == destination[0] && c == destination[1]) return true;

            for (int[] dict : dicts) {
                int nr = r + dict[0];
                int nc = c + dict[1];
                // walk util block by wall(1)
                while (nr < maze.length && nc < maze[0].length && nr >= 0 && nc >= 0 && maze[nr][nc] == 0) {
                    nr += dict[0];
                    nc += dict[1];
                }
                // back to the position before hit the wall
                nr -= dict[0];
                nc -= dict[1];
                if (!visited[nr][nc]) {
                    queue.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

        return false;
    }
}
