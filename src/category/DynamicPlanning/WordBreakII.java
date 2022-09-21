package category.DynamicPlanning;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break-ii/
 * 
 * backtracking problem
 * 
 * Time complexity Analysis: O(2^n)
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
        List<String> dicts = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        new WordBreakII().wordBreak("catsanddog", dicts).forEach(System.out::println);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        dfs(s, wordDict, res, 0, new StringBuilder());
        return res;
    }
    // classical backtracking
    public static void dfs(String s, List<String> wordDict, List<String> res,
                           int start, StringBuilder sb) {
        if (start == s.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String next = s.substring(start, i + 1); // catch: substring from start
            if (wordDict.contains(next)) {
                String origin = sb.toString();
                if (origin.length() == 0) sb.append(next);
                else sb.append(" " + next);
                dfs(s, wordDict, res, i + 1, sb);
                if (origin.length() == 0)
                    sb.delete(sb.length() - next.length(), sb.length());
                else sb.delete(sb.length() - next.length() - 1, sb.length());
            }
        }
    }

    // solution-2
    List<String> dfs1(String s, List<String> wordDict, HashMap<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);

        List<String> subWords = new ArrayList<>();
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                String next = s.substring(word.length());
                if (next.equals("")) { // reach the end
                    subWords.add(word);
                } else {
                    List<String> sublist = dfs1(s.substring(word.length()), wordDict, memo);
                    for (String sub : sublist) {
                        subWords.add(word + " " + sub);
                    }
                }
            }
        }

        System.out.println(s + " : " + Arrays.toString(subWords.toArray()));
        memo.put(s, subWords);
        return subWords;
    }
}
