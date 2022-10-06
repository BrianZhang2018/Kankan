package category.Tree.searchIn2DMatrix;
/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/66140/My-concise-O(m%2Bn)-Java-solution
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * interesting thing is we can convert this matrix to a binary search tree, check the screenshot
 */
public class SearchA2DMatrixII{
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }

        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row < matrix.length) {
            if(target == matrix[row][col]) {
                return true;
            }

            if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}