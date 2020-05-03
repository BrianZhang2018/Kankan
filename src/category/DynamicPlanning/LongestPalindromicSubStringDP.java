package category.DynamicPlanning;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * Compare with LongestPalindromicSubString.java
 *
 * Created by brian zhang on 1/3/20.
 */
public class LongestPalindromicSubStringDP {

    public static void main(String[] args) {

    }

    public String longestPalindrome(String s) {

        if(s == null || s.length() == 0)
            return "";

        int n = s.length();
        String res = null;

        boolean[][] dp = new boolean[n][n];

        //bottom-up, tabulation -> DP
        for (int ws = 1; ws <=n; ws++) {  //ws: windowSize
            for (int start = 0; start <= n - ws; start++) {

                int end = ws + start - 1;

                dp[start][end] = (s.charAt(start)==s.charAt(end)) && (end - start < 3 || dp[start + 1][end - 1]);

                if (dp[start][end] && (res == null || end - start + 1 > res.length())) {
                    res = s.substring(start, end + 1);
                }
            }
        }

        return res;
    }
}
