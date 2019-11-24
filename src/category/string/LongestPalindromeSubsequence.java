package category.string;

/**
 * Created by brianzhang on 8/12/18.
 *
 * Dynamic programming
 */
public class LongestPalindromeSubsequence {

    public static void main(String[] args) {
        System.out.println(getLongestPalindromeSubseq("bbbab"));
    }
//Solution - 1:
    //https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-516-longest-palindromic-subsequence/
    //https://www.youtube.com/watch?v=OZX1nqaQ_9M
    //bottom-top
    public static int getLongestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int sl = s.length();
        int[][] dp = new int[sl][sl];

        //从小规模，子字符串长度为1，开始
        for (int winSize = 1; winSize <= sl; winSize++) {
            for (int start = 0; start <= sl - winSize; start++) {

                int end = start + winSize - 1;

                if (start == end) {
                    dp[start][end] = 1;
                    continue;
                }

                if (s.charAt(start) == s.charAt(end)) {
                    dp[start][end] = dp[start + 1][end - 1] + 2;
                } else {
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
                }
            }
        }

        return dp[0][sl - 1];
    }

//Solution-2, top-bottom
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


//solution-3
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
            for (int startIndex = 0; startIndex <= str.length - winSize; startIndex++) {
                int endIndex = startIndex + winSize - 1;
                if (str[startIndex] == str[endIndex]) {
                    table[startIndex][endIndex] = table[startIndex + 1][endIndex - 1] + 2;
                } else {
                    table[startIndex][endIndex] = Math.max(table[startIndex + 1][endIndex], table[startIndex][endIndex - 1]);
                }
            }
        }

        return table[0][str.length - 1];
    }
}
