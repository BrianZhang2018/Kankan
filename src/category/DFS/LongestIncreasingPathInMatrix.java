package category.DFS;

/**
 * Time Limited Exceeded solution by myself
 *
 */
public class LongestIncreasingPathInMatrix{

    public static void main(String[] args){
        LongestIncreasingPathInMatrix test = new LongestIncreasingPathInMatrix();
        test.longestIncreasingPath(new int[][]{{3,4,5},{3,2,6},{2,2,1}});
    }

    int max = Integer.MIN_VALUE;
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
               dfs(matrix, i, j, matrix[i][j], 0);
            }
        }
        System.out.println(max);
        return max;
    }
    
    public void dfs(int[][] matrix, int i, int j, int val, int count){
        //if the count ==0, means this first time enter into dfs method for this cell in the grid, no previous value, we should skip the below logic
        if(count != 0){
            if(i<0 || j<0 || i>=matrix.length || j>=matrix[0].length || matrix[i][j]<= val){
                if(count > max){
                    max = count;
                }
                return;
            }
        }
        count++;
        dfs(matrix, i+1, j, matrix[i][j], count);
        dfs(matrix, i, j+1, matrix[i][j], count);
        dfs(matrix, i-1, j, matrix[i][j], count);
        dfs(matrix, i, j-1, matrix[i][j], count);
    }
}