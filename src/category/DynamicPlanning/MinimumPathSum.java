package category.DynamicPlanning;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 * <p>
 * Created by brianzhang on 11/25/18.
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        if (grid == null)
            return 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i][j] + grid[i][j - 1], grid[i][j] + grid[i - 1][j]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }
}
