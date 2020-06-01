package category.DFS;

/**
 * https://leetcode.com/problems/max-area-of-island/
 *
 * Created by brianzhang on 2/10/19.
 */
public class MaxAreaOfIsland {
    public static void main(String[] args) {
        //int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        //int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] grid = {{1, 1}, {1, 0}};
        MaxAreaOfIsland test = new MaxAreaOfIsland();
        System.out.println(test.maxAreaOfIsland1(grid));
    }

    // time complexity O(mn)
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int res = dfs(grid, i, j);
                    if (res > maxArea)
                        maxArea = res;
                }
            }
        }
        return maxArea;
    }

    public int dfs(int[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid[0].length || j < 0 || i < 0) {
            return 0;
        }
        if (grid[i][j] == 1) {
            grid[i][j] = 2;
            return 1 + dfs(grid, i + 1, j) + dfs(grid, i, j + 1) + dfs(grid, i - 1, j) + dfs(grid, i, j - 1);
        }
        return 0;
    }

    // 另一种写法
    private int currArea = 0;
    public int maxAreaOfIsland1(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    currArea = 0;
                    dfs1(grid, i, j);
                    if(currArea > maxArea) maxArea = currArea;
                }
            }
        }
        return maxArea;
    }

    public void dfs1(int[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid[0].length || j < 0 || i < 0 || grid[i][j] == 2) {
            return;
        }

        if (grid[i][j] == 1) {
            currArea++;
            grid[i][j] = 2; // marked as visited
            dfs1(grid, i + 1, j);
            dfs1(grid, i, j + 1);
            dfs1(grid, i - 1, j);
            dfs1(grid, i, j - 1);
        }
    }

    // Not recommended way pass an reference into the dfs rather than use the global variables

/*    public int maxAreaOfIsland1(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // if you do int currArea = 0, which won't work as you pass value as value to method rather than pass a reference as value to the method.
                    // So, all dfs method in the dfs only get the copy of primitive value, but if you pass object reference here (e.g. List, Map)
                    // , all dfs methods will get a reference copy for the object so that they all pointing to the same object in heap
                    // the change on that object will be reflected other methods as they are pointing the same object, booooom!
                    Map<String, Integer> currArea = new HashMap<>();
                    currArea.put("currArea", 0);
                    dfs1(grid, i, j, currArea); // pass reference here
                    if (currArea.get("currArea") > maxArea)
                        maxArea = currArea.get("currArea");
                }
            }
        }
        return maxArea;
    }

    public void dfs1(int[][] grid, int i, int j, Map<String, Integer> currArea) {
        if (i >= grid.length || j >= grid[0].length || j < 0 || i < 0 || grid[i][j] == 2) {
            return;
        }

        if (grid[i][j] == 1) {
            currArea.put("currArea", currArea.get("currArea") + 1);
            grid[i][j] = 2; // marked as visited
            dfs1(grid, i + 1, j, currArea);
            dfs1(grid, i, j + 1, currArea);
            dfs1(grid, i - 1, j, currArea);
            dfs1(grid, i, j - 1, currArea);
        }
    }*/
}