package category.DynamicPlanning.String.LongestCommonSubSequence;

/**
 * https://www.geeksforgeeks.org/longest-common-substring-dp-29/
 *
 * 非常像longest Common SubSequence, so compare two problems to understand this questions
 */
public class LongestCommonSubstring{
	public static void main(String[] args) 
	{ 
		String x = "OldSiteGeek";
		String y = "NewSiteGeeks.com";
		System.out.println("Length of Longest Common Substring is " + LCSubStr(x.toCharArray(), y.toCharArray(), x.length(), y.length()));
    }

	static int LCSubStr(char x[], char y[], int m, int n)
	{ 
		// Create a table to store lengths of longest common suffixes of sub strings.
		// Note that dp[i][j] contains length of longest common suffix of X[0..i-1] and Y[0..j-1].
		// The first row and first column entries have no logical meaning, they are used only for simplicity of program
		int dp[][] = new int[m + 1][n + 1];
		int res = 0; // To store length of the longest common substring
		
		// Following steps build dp[m+1][n+1] in bottom up fashion
		for (int i = 1; i <= m; i++)
		{ 
			for (int j = 1; j <= n; j++)
			{ 
				if (x[i - 1] == y[j - 1])
				{ 
					dp[i][j] = dp[i - 1][j - 1] + 1;
					res = Integer.max(res, dp[i][j]);
				} 
				else{
					dp[i][j] = 0;
				}
			}
		} 
		return res;
	} 
}