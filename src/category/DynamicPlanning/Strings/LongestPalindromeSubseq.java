package category.DynamicPlanning.Strings;

/**
 * Dynamic programming
 * 1. get the lenght of longest Palindrome substring
 * 2. get the substring of longest Palindrome
 * Created by brianzhang on 8/12/18.
 */
public class LongestPalindromeSubseq {
    public static void main(String[] args) {
        //get the length of longest Palindrome substring
        System.out.println(getLongestPalindromeSubseq("cbbd"));
        //get the substring of longest Palindrome
        System.out.println(longestPalindrome("cbbd"));
    }

    /**
     * Solution - 1: get the length of longest Palindrome substring
     * https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-516-longest-palindromic-subsequence/
     * bottom-top
     */
    public static int getLongestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int sl = s.length();
        int[][] dp = new int[sl][sl];

        //从小规模，子字符串长度为1，开始
        for (int winSize = 1; winSize <= sl; winSize++) {
            for (int start = 0; start <= sl - winSize; start++) {
                int end = start + winSize - 1;
                //base case
                if (start == end) {
                    dp[start][end] = 1;
                    continue;
                }

                if (s.charAt(start) == s.charAt(end)) { //case 1:
                    dp[start][end] = dp[start + 1][end - 1] + 2;
                } else {// case 2:
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
                }
            }
        }

        return dp[0][sl - 1];
    }

    //https://leetcode.com/problems/longest-palindromic-substring
    //get the substring of longest Palindrome
    public static String longestPalindrome(String s) {
        
        if(s == null || s.length() == 0)
            return "";
      
        int n = s.length();
        String res = null;
        boolean[][] dp = new boolean[n][n];

         //bottom-up, tabulation -> DP
        for (int ws = 1; ws <=n; ws++) {  //ws: windowSize
          for (int start = 0; start <= n - ws; start++) { 
              
            int end = ws + start -1;
            dp[start][end] = s.charAt(start) == s.charAt(end) && (end - start < 3 || dp[start + 1][end - 1]);

            if (dp[start][end] && (res == null || end - start + 1 > res.length())) {
              res = s.substring(start, end + 1);
            }
          }
        }

        return res;
  }

    //Solution-2: top-bottom
    public static int get2(String s) {

        int sl = s.length();
        int[][] dp = new int[sl][sl];
//i, j play as the left and right pointer
        for (int i = sl - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < sl; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    System.out.println(dp[i][j]);
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    System.out.println(dp[i][j]);
                }
            }
        }
        return dp[0][sl - 1];
    }


    //A solution-3
    //步骤最多，最细的一个
    //https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution
    //bottom-up approach
    public static int get3(String s) {

        if (s == null || s.isEmpty()) return 0;
        char[] str = s.toCharArray();
        int[][] table = new int[str.length][str.length];

        for (int i = 0; i < str.length; i++) {
            table[i][i] = 1; // Base case
        }

        for (int winSize = 2; winSize <= str.length; winSize++) {
            for (int start = 0; start <= str.length - winSize; start++) {
                int end = start + winSize - 1;
                if (str[start] == str[end]) {
                    table[start][end] = table[start + 1][end - 1] + 2;
                } else {
                    table[start][end] = Math.max(table[start + 1][end], table[start][end - 1]);
                }
            }
        }

        return table[0][str.length - 1];
    }
}
