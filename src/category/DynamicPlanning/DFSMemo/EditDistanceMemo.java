package category.DynamicPlanning.DFSMemo;

/**
 * https://leetcode.com/problems/edit-distance/
 * time complexity: 1. without memo: 3^n
 *                  2. memo: m * n (Since consider it as 2D array: m = word1.length, n = word2.length)
 *
 * https://www.geeksforgeeks.org/edit-distance-dp-using-memoization/
 *
 * Created by brianzhang on 11/29/20.
 */
public class EditDistanceMemo {

    public static void main(String[] args) {
        System.out.println(minDistance("horse","ros"));
    }

    public static int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        return helper(word1, word2, m, n, new int[m][n]);
    }

    private static int helper(String word1, String word2, int m, int n, int[][] memo) {
        if(m == 0 || n == 0) return m == 0? m : n;

        if(memo[m - 1][n - 1] != 0) {
            return memo[m - 1][n - 1];
        }

        if(word1.charAt(m - 1) == word2.charAt(n - 1)) {
            return helper(word1, word2, m - 1, n - 1, memo);
        }

        int insert = helper(word1, word2, m, n - 1, memo);
        int remove = helper(word1, word2, m - 1, n, memo);
        int replace = helper(word1, word2, m - 1, n - 1, memo);

        memo[m - 1][n - 1] = 1 + Math.min(insert, Math.min(remove, replace));
        return memo[m - 1][n - 1];
    }
}
