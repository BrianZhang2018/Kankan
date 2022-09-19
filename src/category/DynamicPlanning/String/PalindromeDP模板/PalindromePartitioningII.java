package category.DynamicPlanning.String.PalindromeDP模板;

/**
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 *
 * https://leetcode.com/problems/palindrome-partitioning-ii/discuss/42213/Easiest-Java-DP-Solution-(97.36)
 * Created by brianzhang on 1/12/20.
 */
public class PalindromePartitioningII {
    public static void main(String[] args) {
        System.out.println(minCut("aab"));
    }

    public static int minCut(String s) {
        int[] cut = new int[s.length()];
        boolean[][] p = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j < 3 || p[i - 1][j + 1])) {
                    p[i][j] = true;
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }

        return cut[cut.length - 1];
    }
}

// Why i - j < 3  ?
// 1. String of length 1 is always palindrome so no need to check in boolean table
// 2. String of length 2 is palindrome if Ci == Cj which is already checked in first part so no need to check again
// 3. String of length 3 is palindrome if Ci == Cj which is already checked in first part and Ci+1 and Cj-1 is same character which is always a palindrome

