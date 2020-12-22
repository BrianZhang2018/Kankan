package category.DFS.IslandProblems.origin;

/**
 * Flip Number solution for number of island I
 * Created by brianzhang on 4/14/19.
 */
public class NumberOfIslandFlipNumberSolution {

    private int count = 0;

    public int numIslands(char[][] grid) {
    
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] =='1'){
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int i, int j){
        if(i>=grid.length || j>=grid[0].length || j<0 || i<0 || grid[i][j] == '0'){
            return;
        }

        if(grid[i][j] == '1'){
            grid[i][j] = '2';
            dfs(grid, i+1, j);
            dfs(grid, i, j+1);
            dfs(grid, i-1, j);
            dfs(grid, i, j-1);
        }
    }
}
