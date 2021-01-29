package category.Array;

/**
 * https://leetcode.com/problems/sparse-matrix-multiplication/
 *
 * https://leetcode.com/problems/sparse-matrix-multiplication/discuss/146283/Clear-Java-Code-with-Explanation
 *
 * https://leetcode.com/problems/sparse-matrix-multiplication/discuss/76220/My-Java-Solution
 *
 * Created by brianzhang on 1/27/21.
 */
public class SparseMatrixMultiplication {

    public int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length, colsA = A[0].length, colsB = B[0].length;
        int[][] res = new int[rowsA][colsB];
        for (int aRow = 0; aRow < rowsA; aRow++) {
            for (int bRow = 0; bRow < colsA; bRow++) { // B rows = cols A
                if (A[aRow][bRow] != 0) {
                    for (int bCol = 0; bCol < colsB; bCol++) { // loop the bRow row by colsB columns
                        res[aRow][bCol] += A[aRow][bRow] * B[bRow][bCol]; // calculate the each column result gradually in result matrix row A
                    }
                }
            }
        }
        return res;
    }
}