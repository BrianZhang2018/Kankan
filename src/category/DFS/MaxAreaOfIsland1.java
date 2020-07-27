package category.DFS;

/**
 * 另一种写法
 * https://leetcode.com/problems/max-area-of-island/
 *
 * Created by brianzhang on 2/10/19.
 */
public class MaxAreaOfIsland1 {

    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        //int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        int[][] grid = {{1, 1}, {1, 0}};
        //int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        MaxAreaOfIsland1 test = new MaxAreaOfIsland1();
        System.out.println(test.maxAreaOfIsland(grid));
    }

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0)
            return 0;

        grid[i][j] = 0; // flip th number to "0" which make the cell has been visited
        int res = 1;
        for (int[] dir : dirs) {
            res += dfs(grid, dir[0] + i, dir[1] + j);
        }
        return res;
    }
}
