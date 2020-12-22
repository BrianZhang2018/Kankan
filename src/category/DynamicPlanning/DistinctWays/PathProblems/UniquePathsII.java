package category.DynamicPlanning.DistinctWays.PathProblems;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 *
 * Created by brianzhang on 12/20/20.
 */
public class UniquePathsII {
    public static void main(String[] args) {}

    // my solution
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) return 0;

        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(obstacleGrid[i][j] == 1) continue;
                if(i==0 || j==0){ // handle the boundary cells
                    if(i == 0 && j>=1)
                        dp[i][j] = dp[i][j-1];
                    else if(j == 0 && i>=1)
                        dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }

    /**
     * 一维滚动数组
     *    dp[j] += dp[j - 1];
     *    is
     *    new dp[j] = old dp[j] + dp[j-1]
     *    which is current cell = top cell + left cell
     */
    public int uniquePathsWithObstaclesOne(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) return 0;

        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;

        for(int[] row : obstacleGrid){
            for(int j=0; j<n; j++){
                if(row[j] == 1)
                    dp[j] = 0;
                else if(j > 0){
                    dp[j] += dp[j-1];
                }
            }
        }

        return dp[n-1];
    }
}
