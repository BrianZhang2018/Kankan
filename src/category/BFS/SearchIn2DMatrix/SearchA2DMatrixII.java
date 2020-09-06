package category.BFS.SearchIn2DMatrix;
/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * 
 * BFS的经典用法，可以用在很多其他的问题中去寻找一个element在2D matrix中,
 * 而且是最快的方法，快于DFS
 */
public class SearchA2DMatrixII{
    public static void main(String[] args) {

    }
    public boolean searchMatrix(int[][] matrix, int target) {
        
        if(matrix == null || matrix.length == 0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j] == target){
                    return true;
                }else{
                    if(bfs(matrix, i, j, target))
                        return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean bfs(int[][] matrix, int i, int j, int target){
        int[][] dires = new int[][]{{1,0}, {-1, 0}, {0, 1}, {0, -1}};
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        for(int[] dire : dires){
            int nl = i + dire[0];
            int nr = j + dire[1];
            
            if(nl>m-1 || nl<0 || nr>n-1 || nr<0 || matrix[nl][nr] < target){
                continue;
            }
            
            if(matrix[nl][nr] == target){
                return true;
            }
        }
        
        return false;
    }

    // https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/66140/My-concise-O(m%2Bn)-Java-solution
    public boolean searchMatrix1(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}