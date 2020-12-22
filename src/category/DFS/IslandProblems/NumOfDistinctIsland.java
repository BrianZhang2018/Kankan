package category.DFS.IslandProblems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://www.lintcode.com/problem/number-of-distinct-islands/
 * Bloomberg
 *
 * Time complexity: O(rows * cols), Space complexity: O(rows * cols)
 * Created by brianzhang on 5/9/20.
 */
public class NumOfDistinctIsland {

    public static void main(String[] args) {}

    public int numberofDistinctIslands(int[][] grid) {
        Set<String> res = new HashSet<>(); // exclude the island which has the same shapes
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1) {
                    List<int[]> temp = new ArrayList<>();
                    dfs(i, j, i, j, grid, temp);
                    res.add(convertIslandShapeToStr(temp));
                }
            }
        }

        return res.size();
    }

    public void dfs(int i, int j, int x0, int y0, int[][] grid, List<int[]> temp){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j] != 1){
            return;
        }
        if(grid[i][j] == 1){
            temp.add(new int[]{i-x0, j-y0});
            grid[i][j] = 2;
        }

        dfs(i+1, j, x0, y0, grid, temp);
        dfs(i-1, j, x0, y0,  grid, temp);
        dfs(i, j+1, x0, y0, grid, temp);
        dfs(i, j-1, x0, y0,  grid, temp);
    }

    public String convertIslandShapeToStr(List<int[]> pos){
        StringBuilder sb = new StringBuilder();
        for(int[] i : pos) sb.append(i[0]).append(i[1]);
        return sb.toString();
    }
}
