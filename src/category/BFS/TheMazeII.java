package category.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/articles/the-maze-ii/
 * https://www.lintcode.com/problem/the-maze-ii/
 */
public class TheMazeII {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // write your code here
        
        int m = maze.length;
        int n = maze[0].length;
        
        int[][] dist = new int[m][n];
        
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
                int count = 0;
                while(nr>=0 && nr<m && nc>=0 && nc<n && maze[nr][nc] == 0){
                    nr +=  dir[0];
                    nc +=  dir[1];
                    count++;
                }
                
                //"dist[nr-dir[0]][nc-dir[1]]" is the distance of ball stopped location - (wall location - 位移)
                if(dist[s[0]][s[1]] + count < dist[nr-dir[0]][nc-dir[1]]){
                    dist[nr-dir[0]][nc-dir[1]] = dist[s[0]][s[1]] + count;
                    queue.add(new int[]{nr-dir[0], nc-dir[1]});
                }
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }
}