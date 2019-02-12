package category.DynamicPlanning;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by brianzhang on 1/9/19.
 */
public class WordBreakDP {

    public static void main(String[] args) {

        String[] dict = {"leet", "co"};

        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("leet");
        hashSet.add("co");

        System.out.println(wordBreak("leetco", hashSet));
    }

    public static boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) return false;

        int n = s.length();

        // dp[i] represents whether s[0...i] can be formed by dict
        boolean[] dp = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                String sub = s.substring(j, i + 1);

                if (dict.contains(sub) && (j == 0 || dp[j - 1])) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n - 1];
    }
}
