package category.DynamicPlanning.String.PalindromeDP模板;

/**
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 * https://www.youtube.com/watch?v=XNWz9xbX8F0&t=5s
 *
 * LCS solution: LongestCommonSubSequence.java
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/discuss/470687/Java-Longest-Common-Subsequence-Solution-Clean-code
 *
 * Created by brianzhang on 1/5/20.
 */
public class MinimumInsertionStepsToMakeAPalindrome {

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(minInsertions(s));
        System.out.println(minInsertionsLCS(s));
    }

    // Bottom-up, 递推
    public static int minInsertions(String s) {

        if(s == null || s.length() <=1) return 0;

        int n = s.length();
        int[][] dp = new int[n][n];

        for(int l=2; l<=n; l++){ // 要点: 第一层for loop是子问题的规模or长度 (LongestPalindromeSubSequence.java 同样)
            for(int i=0, j=l-1; j<n; i++, j++){
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i+1][j-1] : Math.min(dp[i+1][j], dp[i][j-1]) + 1;
            }
        }

        return dp[0][n-1];
    }

    // longest-common-subsequence Solution - this si applicable for "minimum deletion to make a palindrome" also
    public static int minInsertionsLCS(String s) {
        String sReverse = new StringBuilder(s).reverse().toString();
        int lcs = longestCommonSubSequence(s.toCharArray(), sReverse.toCharArray());
        return s.length() - lcs;
    }

    public static int longestCommonSubSequence(char[] arr1, char[] arr2) {
        int n1 = arr1.length, n2 = arr2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }
}