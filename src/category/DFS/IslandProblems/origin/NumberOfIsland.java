package category.DFS.IslandProblems.origin;

import java.util.*;

/**
 * https://leetcode.com/problems/number-of-islands/
 * http://blog.welkinlan.com/2015/04/09/number-of-islands-leetcode-java-dfs/
 *
 * Time Complexity: O(rows*columns + m*n) = O(rows*columns)
 *
 * DFS时间复杂度：https://stackoverflow.com/questions/50901203/dfs-and-bfs-time-and-space-complexities-of-number-of-islands-on-leetcode
 */
public class NumberOfIsland {
    // DFS
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0'; // mark as visited, sink the island by marking it as '0'
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    // BFS
    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int islands = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0')
                    continue;
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[] { i, j });
                islands++;
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    int r = cur[0], c = cur[1];
                    if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '0')
                        continue;
                    grid[r][c] = '0'; // Mark as visited, sink the island by marking it as '0'
                    queue.add(new int[] { r + 1, c });
                    queue.add(new int[] { r - 1, c });
                    queue.add(new int[] { r, c + 1 });
                    queue.add(new int[] { r, c - 1 });
                }
            }
        }
        return islands;
    }
}
