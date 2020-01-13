package category.DynamicPlanning.Strings.LCSubSequence;

/**
 *
 * https://leetcode.com/problems/longest-common-subsequence/discuss/398711/4-different-ways-to-solve-Longest-Common-Sub-sequence-including-O(N)-Solution
 *
 * dp[i][j], 表示前i个字符和前J个字符的LCS长度
 */
public class LongestCommonSubSequence {

    public static void main(String[] args) {
        LongestCommonSubSequence test = new LongestCommonSubSequence();
        System.out.println(test.longestCommonSubSequence("bacde", "tace"));
    }
    //two dimension
    public int solution1(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        int memo[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    memo[i][j] = 0;
                else if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    memo[i][j] = memo[i - 1][j - 1] + 1;  // why memo[i - 1][j - 1], 因为这是前两个字符（one from each string）比较的结果, 斜对角的的cell
                else
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
            }
        }
        return memo[m][n];
    }

    //solution 1 简写
    public int longestCommonSubSequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        int[][] dp = new int[m+1][n+1];
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        
        return dp[m][n];
    }

    // one dimension
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