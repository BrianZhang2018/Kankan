package category.DynamicPlanning.wordBreakI.dp;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break/
 *
 * time complexity: n^2
 * Created by brianzhang on 1/9/19.
 */
public class WordBreakIDP {
    public static void main(String[] args) {
        System.out.println(wordBreak("leetco", new HashSet<>(Arrays.asList("leet", "co"))));
    }

// Solution-1: DP递推, O(n^2) ("n square" Or "n to the power 2") - Bottom-Up solution
    public static boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) return false;
        int n = s.length();
        boolean[] dp = new boolean[n]; // dp[i] represents whether s[0...i] can be formed by dict

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (dict.contains(s.substring(j, i + 1)) && (j == 0 || dp[j - 1])) {
                    dp[i] = true;
                    break; // remove this, also works
                }
            }
        }
        return dp[n - 1];
    }
}
