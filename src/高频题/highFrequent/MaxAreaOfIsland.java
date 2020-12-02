package 高频题.highFrequent;

/**
 * https://leetcode.com/problems/max-area-of-island/
 *
 * Created by brianzhang on 2/10/19.
 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        //int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        int[][] grid = {{1, 1}, {1, 0}};
        //int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }

    private int maxArea = 0;
    public int maxAreaOfIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(dfs(grid, i, j), maxArea);
                }
            }
        }
        return maxArea;
    }

    public int dfs(int[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid[0].length || j < 0 || i < 0 || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;

        return 1 + dfs(grid, i + 1, j) + dfs(grid, i, j + 1) + dfs(grid, i - 1, j) + dfs(grid, i, j - 1);
    }
}