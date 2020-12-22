package category.DynamicPlanning.DistinctWays.PathProblems;

/**
 * https://leetcode.com/problems/unique-paths/description/
 *
 * Created by brianzhang on 10/14/18.
 */
public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 4));
    }

    public static int uniquePaths(int m, int n) {
        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    grid[i][j] = 1;
                } else {
                    grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
                }
            }
        }
        return grid[n - 1][m - 1];
    }

    // 一维滚动数组
    public int uniquePathsOneDemension(int m, int n) {
        int[] dp = new int[n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 || c == 0) {
                    dp[c] = 1;
                } else {
                    dp[c] += dp[c - 1];
                }

            }
        }
        return dp[n - 1];
    }

}
