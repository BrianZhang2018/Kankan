package category.DynamicPlanning.String.LongestCommonSubSequence;

/**
 * https://leetcode.com/problems/longest-common-subsequence/discuss/398711/4-different-ways-to-solve-Longest-Common-Sub-sequence-including-O(N)-Solution
 *
 * dp[i][j], 表示前i个字符(in text1)和前J个字符(in text2)的LCS长度
 *
 * 变形题： 1. Minimum Insertion/Deletion Steps To Make A Palindrome
 *              - MinimumInsertionStepsToMakeAPalindrome.java
 *              - https://www.techiedelight.com/find-minimum-number-deletions-convert-string-into-palindrome/
 *         2. MinimumNum Of Insertion/Deletion To Target String - MinimumNumOfInsertionDeletionToTargetString.java
 */
public class LongestCommonSubSequence {

    public static void main(String[] args) {
        System.out.println(longestCommonSubSequence("bacde", "tace"));
        System.out.println(longestCommonSubsequenceDFS("bacde", "tace"));
    }

    // Solution-1
    public static int longestCommonSubSequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        
        for(int i=1; i<=m; i++){ // text1
            for(int j=1; j<=n; j++){ // text2
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1; // dp[i - 1][j - 1], 前两个字符（one from each string)
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        
        return dp[m][n];
    }

    // Solution-2: DFS + Memoization - alternative DP solution base on above solution-1
    public static int longestCommonSubsequenceDFS(String text1, String text2) {
        return backtrack(text1, text2, 0, 0, new Integer[text1.length()][text2.length()]);
    }

    public static int backtrack(String text1, String text2, int i, int j, Integer[][] memo) {
        if(i >= text1.length() || j >= text2.length()) return 0;

        if(memo[i][j] != null) return memo[i][j];

        int res;
        if(text1.charAt(i) == text2.charAt(j)){
            res = backtrack(text1, text2, i+1, j+1, memo) + 1;
        }else{
            res = Math.max( backtrack(text1, text2, i, j+1, memo), backtrack(text1, text2, i+1, j, memo));
        }

        memo[i][j] = res;

        return res;
    }

    // Solution-3: One Dimension
    public int solution(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[] dp = new int[n + 1]; //one dimension rolling array

        for (int i = 1; i <= m; i++) {
            int prev = 0;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = temp;
            }
        }
        return dp[n];
    }

}