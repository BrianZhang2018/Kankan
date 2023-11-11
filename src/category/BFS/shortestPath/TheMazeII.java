package category.BFS.shortestPath;

import java.util.*;

/**
 * https://leetcode.com/articles/the-maze-ii/
 * https://www.lintcode.com/problem/the-maze-ii/
 *
 * find the shortest path to the target, so we have to traverse all the path from topleft to bottomright,
 * we can't use the visited here as one cell can be part of different path, we don't want to skip it.
 * The right way for find all the path is dfs+backtracking as BFS does not do any backtracking.
 *
 * TC: O(m*n*max(m,n))
 * Complete traversal of maze will be done in the worst case. Here, mm and nn refers to the number of rows and columns
 * of the maze. Further, for every current node chosen, we can travel upto a maximum depth of max(m,n) in any direction.
 */
public class TheMazeII {

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n]; // memo the distance
        for(int[] row : dist){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[start[0]][start[1]] = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(start);
        int[][] dirs = new int[][]{{0,1}, {1,0}, {-1, 0}, {0,-1}};
        while(!queue.isEmpty()){
            int[] s = queue.poll();
            for(int[] dir : dirs){
                int nr = s[0] + dir[0];
                int nc = s[1] + dir[1];
                int steps = 0;
                while(nr>=0 && nr<m && nc>=0 && nc<n && maze[nr][nc] == 0){
                    nr += dir[0];
                    nc += dir[1];
                    steps++;
                }
                // back to position before hit the wall
                nr-=dir[0];
                nc-=dir[1];
                if(dist[s[0]][s[1]] + steps < dist[nr][nc]){
                    dist[nr][nc] = dist[s[0]][s[1]] + steps;
                    if(nr != destination[0] || nc != destination[1]){ // 如果滚到target cell，就不需要再滚了
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }

        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }
}