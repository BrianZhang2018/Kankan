package category.DynamicPlanning;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break-ii/
 * 
 * backtracking problem
 * 
 * Time complexity Analysis:
 * 1. One way to explain O(2^n) is as follows: given an array of length n, there are n+1 ways/intervals to partition it into two parts.
 * Each interval has two choices - split or not. In the worse case, we will have to check all possibilities,
 * which becomes O(2^(n+1)) -> O(2^n). This analysis is similar to palindrome partitioning.
 *
 * 2. In the worst case the runtime of this algorithm is O(2^n).
 * Consider the input "aaaaaa", with wordDict = ["a", "aa", "aaa", "aaaa", "aaaaa", "aaaaa"]. Every possible partition is a valid sentence, and there are 2^n-1 such partitions.
 * It should be clear that the algorithm cannot do better than this since it generates all valid sentences. The cost of iterating over cached results will be exponential, as every possible partition will be cached, resulting in the same runtime as regular backtracking. Likewise, the space complexity will also be O(2^n) for the same reason - every partition is stored in memory.
 * Where this algorithm improves on regular backtracking is in a case like this: "aaaaab", with wordDict = ["a", "aa", "aaa", "aaaa", "aaaaa", "aaaaa"], i.e. the worst case scenario for Word Break I, where no partition is valid due to the last letter 'b'. In this case there are no cached results, and the runtime improves from O(2^n) to O(n^2).
 * 
 * Created by brianzhang on 2/20/19.
 */
public class WordBreakII {
    public static void main(String[] args) {
        WordBreakII wordBreakII = new WordBreakII();
        List<String> dicts = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        wordBreakII.wordBreak("catsanddog", dicts).forEach(s -> System.out.println(s));

        System.out.println("str".substring(3));
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<>());
    }

    // backtracking
    List<String> dfs(String s, List<String> wordDict, HashMap<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);

        List<String> subWords = new ArrayList<>();

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                String next = s.substring(word.length());
                if(next.equals("")) // reach the end
                    subWords.add(word);
                else{
                    List<String> sublist = dfs(s.substring(word.length()), wordDict, memo);
                    for (String sub : sublist) subWords.add(word + " " + sub);
                }
            }
        }

        memo.put(s, subWords);
        System.out.println(s + " : " + Arrays.toString(subWords.toArray()));
        return subWords;
    }
}
