package category.DynamicPlanning.String.PalindromeDP模板;

/**
 * https://leetcode.com/problems/longest-palindromic-substring
 *
 * Created by brianzhang on 3/15/21.
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("cabad"));
    }

    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return "";

        int n = s.length();
        String res = null;
        boolean[][] dp = new boolean[n][n];
        // bottom-up, tabulation | 模板方法对于这类 palindrome 问题，e.g. MinimumInsertionStepsToMakeAPalindrome.java, PalindromePartitioningII
        for (int ws = 1; ws <= n; ws++) {  // ws: windowSize, 问题规模. (wz=1, but subsequence is =2)
            for (int l = 0, r = ws - 1; r < n; l++, r++) {
                dp[l][r] = s.charAt(l) == s.charAt(r) && (r - l < 3 || dp[l + 1][r - 1]);
                // r-l < 3 for cases like a, aa, aba which are palindrome.
                // Beyond length 3, check prev dp[start + 1][end - 1]
                if (dp[l][r] && (res == null || r - l + 1 > res.length())) {
                    res = s.substring(l, r + 1);
                }
            }
        }

        return res;
    }

    // simplified DP - my solution
    public String longestPalindrome1(String s) {
        if (s.length() < 2) return s;

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String res = "";
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) { // another thought how to get the substring
                dp[j][i] = s.charAt(i) == s.charAt(j) && (i - j < 3 || dp[j + 1][i - 1]);
                if (dp[j][i]) {
                    res = res.length() > i - j + 1 ? res : s.substring(j, i + 1);
                }
            }
        }

        return res;
    }

}
