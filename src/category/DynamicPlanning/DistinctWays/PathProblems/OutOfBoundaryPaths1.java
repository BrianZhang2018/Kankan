package category.DynamicPlanning.DistinctWays.PathProblems;

/**
 * https://leetcode.com/problems/out-of-boundary-paths/
 *
 * Created by brianzhang on 2/25/19.
 */
public class OutOfBoundaryPaths1 {

    public static void main(String[] args) {
        System.out.println(findPaths(2,2,2,0,0));
    }

    public static int findPaths(int m, int n, int N, int i, int j) {

        int mod = 1000000007;  //The answer may be very large, return it after mod 109 + 7 (given in the question)
        int[][][] dp = new int[N + 1][m][n];
        int[][] dicts = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        for (int s = 1; s <= N; s++) {
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    for (int[] dict : dicts) {
                        int l = x + dict[0];
                        int r = y + dict[1];
                        if (l < 0 || r < 0 || l >= m || r >= n)
                            dp[s][x][y] += 1;
                        else
                            dp[s][x][y] = (dp[s][x][y] + dp[s - 1][l][r]) % mod;
                    }
                }
            }
        }

        return dp[N][i][j];
    }
}
