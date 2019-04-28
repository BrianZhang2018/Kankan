package category.DFS;

/**
 * Created by brianzhang on 2/10/19.
 */
public class MaxAreaOfIslandVisitedArray {

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        //int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        int[][] grid = {{1, 1}, {1, 0}};
        //int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        MaxAreaOfIslandVisitedArray maxAreaOfIsland = new MaxAreaOfIslandVisitedArray();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
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
        //flip th number which mark the cell as visited
        grid[i][j] = 0;
        int res = 1;
        for (int[] dir : dirs) {
            int i1 = dir[0] + i;
            int j1 = dir[1] + j;
            res += dfs(grid, i1, j1);
        }
        return res;
    }
}
