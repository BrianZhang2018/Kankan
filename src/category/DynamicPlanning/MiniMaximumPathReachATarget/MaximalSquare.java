package category.DynamicPlanning.MiniMaximumPathReachATarget;

/**
 * Great Article: https://leetcode.com/articles/maximal-square/ ,
 * which has brute force, two dimensional array and one dimensional solution
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

    public int maximalSquareOneDimension(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n+1];
        int maxLen = 0, prev = 0;
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                int temp = dp[j];
                if(matrix[i-1][j-1] == '1'){
                    dp[j] = Math.min(Math.min(dp[j-1], prev), dp[j]) +1;
                    maxLen = Math.max(maxLen, dp[j]);
                }else{
                    dp[j] = 0;
                }
                prev = temp;
            }
        }

        return maxLen * maxLen;
    }

}
