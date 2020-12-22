package category.DFS.IslandProblems;

/**
 * https://leetcode.com/problems/making-a-large-island/
 * 应该秒这题
 *
 * Time Complexity: O(m*n), In DFS every cell I search at most once.
 *
 * Created by brianzhang on 6/2/20.
 */
public class MakeALargerIsland {
    public static void main(String[] args) {
        MakeALargerIsland test = new MakeALargerIsland();
        System.out.println(test.largestIsland(new int[][]{{0,0}, {0,1}}));
    }

    public int largestIsland(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int max = -1;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0){
                    grid[i][j] = 1;
                    int count = dfs(grid, i, j, new boolean[m][n]);
                    max = Math.max(count, max);
                    grid[i][j] = 0;
                }
            }
        }
        return max == -1 ? grid.length * grid[0].length : max;
    }

    public int dfs(int[][] grid, int i, int j, boolean[][] visited){
        if(i >=grid.length || i<0 || j>=grid[0].length || j<0 || visited[i][j] || grid[i][j] == 0 ){
            return 0;
        }
        visited[i][j] = true;
        return 1 + dfs(grid, i, j+1, visited) + dfs(grid, i+1, j, visited) + dfs(grid, i-1, j, visited) + dfs(grid, i, j-1, visited);
    }
}
