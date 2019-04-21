package category.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 2/10/19.
 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        //int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        int[][] grid = {{1, 1}, {1, 0}};
        //int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland1(grid));
    }

    private int maxArea = 0;

    public int maxAreaOfIsland(int[][] grid) {
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

    //another solution which base on the 'Numbers of Island I'
    public int maxAreaOfIsland1(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    //if you do int currArea = 0, which won't work as you pass value as value to method
                    //rather than pass a reference as value to the method. So, all dfs method in the dfs
                    //only get the copy of primitive value, but if you do pass object reference here (e.g. List)
                    //, all dfs methods will get a reference copy for the object so that they all pointing to the same object in heap
                    // the change on that object will be reflected other methods as they are pointing the same object, booooom!
                    List<Integer> currArea = new ArrayList<Integer>();
                    dfs1(grid, i, j, currArea);
                    if (currArea.get(currArea.size() - 1) > maxArea)
                        maxArea = currArea.get(currArea.size() - 1);
                }
            }
        }
        return maxArea;
    }

    public void dfs1(int[][] grid, int i, int j, List<Integer> currArea) {
        if (i >= grid.length || j >= grid[0].length || j < 0 || i < 0) {
            return;
        }

        if (grid[i][j] == 1) {
            if (currArea.size() == 0)
                currArea.add(1);
            else{
                int temp = currArea.get(currArea.size() - 1);
                temp++;
                currArea.add(temp);
            }

            grid[i][j] = 2;
            dfs1(grid, i + 1, j, currArea);
            dfs1(grid, i, j + 1, currArea);
            dfs1(grid, i - 1, j, currArea);
            dfs1(grid, i, j - 1, currArea);
        }
    }
}