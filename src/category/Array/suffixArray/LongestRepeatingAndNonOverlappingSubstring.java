package category.Array.suffixArray;

/**
 * solution source:
 * https://www.geeksforgeeks.org/longest-repeating-and-non-overlapping-substring/
 *
 * algorithm video course:
 * https://www.coursera.org/lecture/cs-algorithms-theory-machines/longest-repeated-substring-hkJBt
 *
 * DP + Suffix String
 * reference:
 * https://hackerranksolutionc.blogspot.com/2018/02/longest-repeating-and-non-overlapping.html
 *
 * Created by brianzhang on 3/3/19.
 */
public class LongestRepeatingAndNonOverlappingSubstring {

    public static void main(String[] args) {
        System.out.println(solution("geeksforgeeks"));
    }

    public static String solution(String str) {

        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];

        String res = "";
        int maxLength = 0;
        int index = 0;
        //i and j is the index of character in String, LRCRe[i][j] store the length of LRCRe which 'i; is ending index of of prefix string
        // and 'j' is the ending index for suffix string.
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (str.charAt(i - 1) == str.charAt(j - 1) && dp[i - 1][j - 1] < (j - i)) {
                    //LRCRe[i][j]: don't include the i th, j th character
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        //updating the index of prefix substring
                        index = Math.max(i, index);
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return maxLength > 0 ? str.substring(index - maxLength, index) : "";

    }
}
