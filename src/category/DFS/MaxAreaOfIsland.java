package category.DFS;

/**
 * https://leetcode.com/problems/max-area-of-island/
 *
 * Time Complexity: O(R*C), where RR is the number of rows in the given grid, and CC is the number of columns. We visit every square once.
 *
 * Created by brianzhang on 2/10/19.
 */
public class MaxAreaOfIsland {
    public static void main(String[] args) {
        //int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        //int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] grid = {{1, 1}, {1, 0}};
        MaxAreaOfIsland test = new MaxAreaOfIsland();
        System.out.println(test.maxAreaOfIsland(grid));
        //System.out.println(test.maxAreaOfIsland1(grid));
    }

    // time complexity O(mn)
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
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
        if (i >= grid.length || j >= grid[0].length || j < 0 || i < 0 || grid[i][j] == 0)
            return 0;

        grid[i][j] = 0; // mark as visited by flipping the number
        return 1 + dfs(grid, i + 1, j) + dfs(grid, i, j + 1) + dfs(grid, i - 1, j) + dfs(grid, i, j - 1);
    }

    // 另一种写法 - use global variable
    private int currArea = 0;
    public int maxAreaOfIsland1(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    currArea = 0;
                    dfs1(grid, i, j);
                    maxArea = Math.max(maxArea, currArea);
                }
            }
        }
        return maxArea;
    }

    public void dfs1(int[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid[0].length || j < 0 || i < 0 || grid[i][j] == 2) return;

        currArea++;
        grid[i][j] = 2; // marked as visited
        dfs1(grid, i + 1, j);
        dfs1(grid, i, j + 1);
        dfs1(grid, i - 1, j);
        dfs1(grid, i, j - 1);
    }
}