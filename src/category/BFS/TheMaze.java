package category.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 把BFS套路看懂了，做这题so easy。参阅：/Users/brianzhang/workspace/WaWahaha/src/DFS vs BFS
 *
 * https://www.lintcode.com/problem/the-maze/my-submissions
 * Created by brianzhang on 4/9/19.
 */
public class TheMaze {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0 || start == null || start.length == 0 || destination == null || destination.length ==0){
            return false;
        }
        int[][] dicts = new int[][]{{1,0}, {0,1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]]= true;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            if(r == destination[0] && c == destination[1])
                return true;

            for(int[] dict : dicts){
                int nr = r + dict[0];
                int nc = c + dict[1];
                //walk util block by wall (1)
                while(nr<maze.length && nc<maze[0].length && nr>=0 && nc>=0 && maze[nr][nc] == 0){
                    nr += dict[0];
                    nc += dict[1];
                }
                //nr-dict[0], nc-dict[1] is previous place before the wall which ball stop there
                if(!visited[nr-dict[0]][nc-dict[1]]){
                    queue.add(new int[]{nr-dict[0], nc-dict[1]});
                    visited[nr-dict[0]][nc-dict[1]] = true;
                }
            }
        }
        return false;
    }
}
