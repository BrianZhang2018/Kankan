package category.DynamicPlanning.String.PalindromeDP模板;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 *
 * Created by brianzhang on 8/12/18.
 */
public class LongestPalindromicSubSequence {
    public static void main(String[] args) {
        System.out.println(getLongestPalindromeSubseq("c"));
       // System.out.println(longestPalindromeSubseq("cbbd"));
    }

    // Solution - 1: DP bottom-top tabulation
    public static int getLongestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {   // base case
            dp[i][i] = 1;
        }
        // 要点：模板方法对于这类 palindrome 问题，e.g. MinimumInsertionStepsToMakeAPalindrome.java
        for (int wz = 2; wz <= n; wz++) { // ws: windowSize, 问题规模
            for (int l = 0, r = wz - 1; r < n; l++, r++) { // shift the window by increasing the i and j
                if (s.charAt(l) == s.charAt(r)) {
                    dp[l][r] = dp[l + 1][r - 1] + 2;
                } else {
                    dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                }
            }
        }

        return dp[0][n-1]; //
    }

    // Solution-2: https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution
    // Top -> bottom recursive method with memoization
    public static int longestPalindromeSubseq(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }

    private static int helper(String s, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (i > j)  return 0; // invalid case
        if (i == j) return 1; // base case

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        }
        else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }

        return memo[i][j];
    }
}
