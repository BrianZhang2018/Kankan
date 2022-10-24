package category.DynamicPlanning.String.LongestCommonSubSequence;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/longest-common-substring-dp-29/
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 *
 * dp[i][j] means the length of the common of X[0..i-1] and Y[0..j-1]
 * e.g.
 * x[0], y[0] (x[1-1], y[1-1])
 * dp[1][1] (the first row and column are assistance row and column with 0, no meaning.
 * 			 it starts from 1 to represent the first X character)
 *
 * 非常像longest Common SubSequence, so compare two problems together to understand
 */
public class LongestCommonSubstring{
	public static void main(String[] args) {
		String x = "OldSiteGeek", y = "NewSiteGeeks.com";
		System.out.println(lcSubstring(x.toCharArray(), y.toCharArray()));
    }

	static String lcSubstring(char x[], char y[]) {
		// dp[i][j] contains length of the longest common substring of X[0..i-1] and Y[0..j-1]
		// The first row and first column entries have no logical meaning, they are used only for simplicity of program
		int m = x.length, n = y.length;
		int dp[][] = new int[m + 1][n + 1];
		int max = 0; // the length of the longest common substring
		char[] longestCommonSubStr = null;
		// Following steps build dp[m+1][n+1] in bottom up fashion
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (x[i - 1] == y[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					if (dp[i][j] > max) {
						max = dp[i][j];
						longestCommonSubStr = Arrays.copyOfRange(x, i - max, i);
					}
				} else {
					dp[i][j] = 0; // can skip since dp[][] initiated with zero
				}
			}
		}

		return String.valueOf(longestCommonSubStr);
	} 
}