package category.BFS.shortestPath.recordPath;

import java.util.*;

/**
 * https://leetcode.com/problems/shortest-path-to-get-all-keys/
 *
 * Created by brianzhang on 5/20/21.
 */
public class ShortestPathGetAllKeys {

    public static void main(String[] args) {
        System.out.println(shortestPathAllKeys(new String[]{"@...a",".###A","b.BCc"}));
    }

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();

        Queue<int[]> queue = new LinkedList();
        int dest = 0;
        boolean[][][] visited = new boolean[m][n][1<<6];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                char c = grid[i].charAt(j);
                if(c == '@'){
                    queue.offer(new int[]{i, j, 0});
                    visited [i][j][0] = true;
                }else if(c >= 'a' && c <= 'f')
                    dest |= 1 << (c -'a'); // 1110 means a,b,c in the grid
            }
        }

        int step = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                int state = cur[2]; // state: record path

                for (int[] dir : DIRS) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }
                    char c = grid[nx].charAt(ny);
                    int newState = state;
                    if (c == '#') { // wall
                        continue;
                    }
                    if (c >= 'A' && c <= 'F' && (newState & (1 << (c - 'A'))) == 0) { // no key
                        continue;
                    }
                    if (c >= 'a' && c <= 'f') { // pick up key
                        newState |= 1 << (c - 'a');
                    }
                    if (visited[nx][ny][newState]) { // skip visited
                        continue;
                    }
                    if (newState == dest) { // find all the keys
                        return step + 1;
                    }
                    visited[nx][ny][newState] = true;
                    queue.offer(new int[]{nx, ny, newState});
                }
            }
            step++;
        }
        return -1;
    }
}
