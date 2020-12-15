package category.DynamicPlanning;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break/
 *
 * time complexity: n^2
 * Created by brianzhang on 1/9/19.
 */
public class WordBreakI {
    public static void main(String[] args) {
        //solution-1
       // System.out.println(wordBreak("leetco", new HashSet<>(Arrays.asList("leet", "co"))));

        //solution-2
        System.out.println(wordBreakDFSMemo("catsandog", new HashSet<>(Arrays.asList("cats","dog","sand","and","cat")), new HashMap<>()));
        res.forEach(s -> System.out.println(s));
    }

    // DP递推, O(n^2) ("n square" Or "n to the power of 2") - Bottom-Up solution
    public static boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) return false;

        int n = s.length();
        boolean[] dp = new boolean[n]; // dp[i] represents whether s[0...i] can be formed by dict

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (dict.contains(s.substring(j, i + 1)) && (j == 0 || dp[j - 1])) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n - 1];
    }

    static List<String> res = new ArrayList<>();

    // DFS (记忆化递归), time complexity: n^2, Top-Down solution
    public static boolean wordBreakDFSMemo(String s, Set<String> dict, Map<String, Boolean> memo) {
        if (memo.containsKey(s))
            return memo.get(s);

        if (dict.contains(s)) {
            res.add(s);
            memo.put(s, true);
            return true;
        }

        for (int i = 1; i < s.length(); i++) {
            // For '&&' condition, if 'dict.contains(s.substring(0, i))' is false, won't run the ' wordBreak(s.substring(i), dict, memo)'
            if (dict.contains(s.substring(0, i)) && wordBreakDFSMemo(s.substring(i), dict, memo)) {
                res.add(s.substring(0, i));
                memo.put(s, true);
                return true;
            }
        }

        memo.put(s, false);
        return false;
    }

    // BFS, https://leetcode.com/problems/word-break/discuss/43797/A-solution-using-BFS
    public static boolean wordBreakBFSMemo(String s, Set<String> wordDict) {
        if (wordDict.contains(s)) return true;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        // use a set to record checked index to avoid repeated work.
        // This is the key to reduce the running time to O(N^2).
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(0);
        while (!queue.isEmpty()) {
            int curIdx = queue.poll();
            for (int i = curIdx+1; i <= s.length(); i++) {
                if (visited.contains(i)) continue;
                if (wordDict.contains(s.substring(curIdx, i))) {
                    if (i == s.length()) return true;
                    queue.offer(i);
                    visited.add(i);
                }
            }
        }
        return false;
    }
}
