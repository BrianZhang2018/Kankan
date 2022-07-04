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

        // bottom-up, tabulation | 模板方法对于这类 palindrome 问题，e.g. MinimumInsertionStepsToMakeAPalindrome.java
        for (int ws = 1; ws <=n; ws++) {  // ws: windowSize
            for (int start = 0, end = ws-1; end < n; start++, end++) {

                dp[start][end] = s.charAt(start) == s.charAt(end) && (end - start < 3 || dp[start + 1][end - 1]);

                if (dp[start][end] && (res == null || end - start + 1 > res.length())) {
                    res = s.substring(start, end + 1);
                }
            }
        }

        return res;
    }

}
