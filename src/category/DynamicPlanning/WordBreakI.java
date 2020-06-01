package category.DynamicPlanning;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break/
 * 
 * Created by brianzhang on 1/9/19.
 */
public class WordBreakI {
    public static void main(String[] args) {
        //solution-1
        HashSet<String> wordDict = new HashSet<>();
        wordDict.add("leet");wordDict.add("co");
        //System.out.println(wordBreak("leetco", wordDict));

        //solution-2
        Map<String, Boolean> memo = new HashMap<>();
        HashSet<String> words = new HashSet<>(Arrays.asList("cat", "son", "goal", "song"));
        System.out.println(wordBreakDFSMem("songoalcat", words, memo));
        for(String key : memo.keySet()) System.out.println(key);

        res.forEach(s -> System.out.println(s)); // "song" won't be in res as it can't make a valid words break with other words
    }

    //DP递推, O(n^2) ("n square" Or "n to the power of 2") - Bottom-Up solution
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

    static List<String> res = new ArrayList<>();

    //DFS递归, time complexity: n! (factorial n) - Top-Down solution
    public static boolean wordBreakDFSMem(String s, Set<String> dict, Map<String, Boolean> mem) {
        if (mem.containsKey(s))
            return mem.get(s);

        if (dict.contains(s)) {
            res.add(s);
            mem.put(s, true);
            return true;
        }

        for (int i = 1; i < s.length(); i++) {
            // For '&&' condition, if 'dict.contains(s.substring(i))' is false, won't run the ' wordBreak(s.substring(0, i), mem, dict)' 
            if (dict.contains(s.substring(0, i)) && wordBreakDFSMem(s.substring(i), dict, mem)) {
                res.add(s.substring(0,i));
                mem.put(s, true);
                return true;
            }
        }
        mem.put(s, false);
        return false;
    }
}
