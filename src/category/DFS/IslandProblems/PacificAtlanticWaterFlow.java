package category.DFS.IslandProblems;

import java.util.*;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 */
public class PacificAtlanticWaterFlow {

    // DP
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int m = matrix.length, n = matrix[0].length;
        //visited[][], memo
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
       
        //Start from first and last column
        for(int i=0; i<m; i++){
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, n-1);
        }
        //Start from first and last row
        for(int i=0; i<n; i++){
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i); // start from (0, 1), (0,2), (0,3)...
            dfs(matrix, atlantic, Integer.MIN_VALUE, m-1, i);
        }
        //post processing
        for (int i = 0; i < m; i++) 
            for (int j = 0; j < n; j++) 
                if (pacific[i][j] && atlantic[i][j]) //if the both cell are true, which means water can flow to both ocean
                    res.add(Arrays.asList(i, j));
        
        return res;
    }

    // DFS
    int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    
    public void dfs(int[][] matrix, boolean[][] visited, int height, int x, int y){
        int m = matrix.length, n = matrix[0].length;
        if(x<0 || x>=m || y<0 || y>=n || visited[x][y] || matrix[x][y] < height) // matrix[x][y] < height !!!
            return;
        visited[x][y] = true;
        for(int[] d : dirs){
            dfs(matrix, visited, matrix[x][y], x + d[0], y + d[1]);
        }
    }
}