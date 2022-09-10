package category.BFS;

import java.util.*;

/**
 * https://www.lintcode.com/problem/walls-and-gates/
 *
 * Created by brianzhang on 4/26/20.
 */

public class WallsAndGates {

    public static void main(String[] args) {
        WallsAndGates wa = new WallsAndGates();
        int[][] rooms = new int[][]{{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}};
        wa.wallsAndGatesDFS(rooms);
        for(int[] i : rooms){
            System.out.println(Arrays.toString(i));
        }
    }

    // BFS Solution
    public void wallsAndGatesBFS(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();

        // opposite way: gate to empty room
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int distance = rooms[p[0]][p[1]];
            for (int[] dir : dirs) {
                int nx = p[0] + dir[0];
                int ny = p[1] + dir[1];
                if (nx < m && nx >= 0 && ny < n && ny >= 0 && rooms[nx][ny] != -1) { // careful nx, ny ">=" 0
                    if (rooms[nx][ny] == Integer.MAX_VALUE) {
                        rooms[nx][ny] = distance + 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    // DFS Solution
    public void wallsAndGatesDFS(int[][] rooms) {
        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms[0].length; ++j) {
                if(rooms[i][j] == 0)
                    dfs(i, j, 0, rooms);
            }
        }
    }

    public void dfs(int i, int j, int distance, int[][] rooms){
        if(i<0 || i>=rooms.length || j<0 || j>=rooms[0].length || rooms[i][j] < distance){
            return;
        }
        rooms[i][j] = distance;
        dfs(i, j+1, distance + 1, rooms);
        dfs(i, j-1, distance + 1, rooms);
        dfs(i+1, j, distance + 1, rooms);
        dfs(i-1, j, distance + 1, rooms);
    }
}
