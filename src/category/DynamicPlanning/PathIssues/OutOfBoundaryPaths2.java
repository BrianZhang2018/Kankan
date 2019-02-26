package category.DynamicPlanning.PathIssues;

/**
 * Created by brianzhang on 2/25/19.
 */
public class OutOfBoundaryPaths2 {
    int mod = 1000000007;
    int m;
    int n;

    public int findPaths(int m, int n, int N, int i, int j) {
        this.m = m;
        this.n = n;
        int[][][] dp = new int[N + 1][m][n];
        for (int c = 0; c <= N; ++c) {
            for (int a = 0; a < m; ++a) {
                for (int b = 0; b < n; ++b) {
                    dp[c][a][b] = -1;
                }
            }
        }
        return solve(N, i, j, dp);
    }

    private int solve(int N, int i, int j, int[][][] dp) {
        if (i < 0 || i >= m || j < 0 || j >= n) return 1;
        if (N == 0) return 0;
        if (dp[N][i][j] != -1) return dp[N][i][j];
        long ans = 0;
        ans += solve(N - 1, i - 1, j, dp);
        ans += solve(N - 1, i + 1, j, dp);
        ans += solve(N - 1, i, j - 1, dp);
        ans += solve(N - 1, i, j + 1, dp);
        dp[N][i][j] = (int) (ans % mod);
        return dp[N][i][j];
    }
}
