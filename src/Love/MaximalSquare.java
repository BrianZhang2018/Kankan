package Love;

/**
 * 这个article， 一定要读一下，非常好，对于理解 DP 问题
 * Great Article: https://leetcode.com/articles/maximal-square/
 * , which has brute force, two dimensional array and one dimensional Asolution
 *
 * Created by brianzhang on 4/17/19.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }
}
