package category.DynamicPlanning.Strings;

/**
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 * https://www.youtube.com/watch?v=XNWz9xbX8F0&t=5s
 *
 * compare with LongestCommonSubSequence.java as below smart solution which use the lCS solution to solve this problem:
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/discuss/470687/Java-Longest-Common-Subsequence-Solution-Clean-code
 *
 * Created by brianzhang on 1/5/20.
 */
public class MinimumInsertionStepsToMakePalindrome {

    public static void main(String[] args) {
        String s = "leetcode";
        MinimumInsertionStepsToMakePalindrome test = new MinimumInsertionStepsToMakePalindrome();
        System.out.println(test.minInsertions(s));


        String sReverse = new StringBuilder(s).reverse().toString();
        int lcs = test.longestCommonSubSequence(s.toCharArray(), sReverse.toCharArray());
        System.out.println(s.length() - lcs);
    }

    //bottom-up, 递推
    public int minInsertions(String s) {

        if(s == null || s.length() <=1)
            return 0;

        int n = s.length();
        int[][] dp = new int[n][n];

        for(int l=2; l<=n; l++){ // 第一层for loop是子问题的规模or长度
            for(int i=0, j=l-1; j<n; i++, j++){//
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i+1][j-1] : Math.min(dp[i+1][j], dp[i][j-1]) +1;
            }
        }

        return dp[0][n-1];
    }

    //LCS solution - tricky
    public int longestCommonSubSequence(char[] arr1, char[] arr2) {
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
