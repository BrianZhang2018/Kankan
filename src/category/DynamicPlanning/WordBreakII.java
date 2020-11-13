package category.DynamicPlanning;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break-ii/
 * 
 * this's a backtracking problem
 * 
 * Time complexity:
 * Lets say total number of words be w, and the string length of s be n. then at each char of n places
 * we would check by w possibilities. I believe it is w^(n) - time complexity for general idea of above code.
 * 
 * Created by brianzhang on 2/20/19.
 */
public class WordBreakII {
    public static void main(String[] args) {
        WordBreakII wordBreakII = new WordBreakII();
        List<String> dicts = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        wordBreakII.wordBreak("catsanddog", dicts).forEach(s -> System.out.println(s));
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<>());
    }

    // DFS function returns an array including all substring derived from s - backtracking
    List<String> dfs(String s, List<String> wordDict, HashMap<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);

        List<String> subWords = new ArrayList<>();
        if (s.length() == 0) {
            subWords.add("");
            return subWords;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = dfs(s.substring(word.length()), wordDict, memo);
                for (String sub : sublist)
                    subWords.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }

        memo.put(s, subWords);
        return subWords;
    }
}
