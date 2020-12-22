package category.DFS.IslandProblems.enclavesOrClosed;

/**
 * https://leetcode.com/problems/number-of-enclaves/
 *
 * Created by brianzhang on 12/22/20.
 */
public class NumberOfEnclavesIsland {
    public static void main(String[] args) {}

    boolean flag = true;
    public int numEnclaves(int[][] grid) {

        int numOfIslands = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1){
                    int temp = dfs(grid, i, j);
                    if(flag){
                        numOfIslands += temp;
                    }
                    flag = true;
                }
            }
        }

        return numOfIslands;
    }


    public int dfs(int[][] grid, int i, int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j] == 0)
            return 0;

        if((i ==0 || j==0 || i ==grid.length-1 || j == grid[0].length-1) && grid[i][j] == 1){
            flag = false;
            return 0;
        }

        grid[i][j] = 0;

        return 1 + dfs(grid, i+1, j) + dfs(grid, i-1, j) + dfs(grid, i, j+1) + dfs(grid, i, j-1);
    }
}
