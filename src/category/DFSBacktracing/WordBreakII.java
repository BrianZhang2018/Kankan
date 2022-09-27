package category.DFSBacktracing;

import java.util.*;
import java.util.stream.Collectors;

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
        wordBreak("catsanddog", new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog")))
                .forEach(System.out::println);
        wordBreak("goalspecial",  new ArrayList<>(Arrays.asList("go","goal","goals","special")))
                .forEach(System.out::println);
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<List<String>> res = new ArrayList<>();
   /*     dfs(s, wordDict, res, 0, new ArrayList<>());
        return res.stream().map(list -> String.join(" ", list)).collect(Collectors.toList());*/
        return dfs(s, wordDict, new HashMap<>());
    }

    // solution-2, classical backtracking + memo
    static List<String> dfs(String s, List<String> wordDict, HashMap<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);

        List<String> subWords = new ArrayList<>();
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                String next = s.substring(word.length());
                if (next.equals("")) { // reach the end
                    subWords.add(word);
                } else {
                    List<String> sublist = dfs(s.substring(word.length()), wordDict, memo);
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

    // Simplified backtracking
    // this solution can't use Memo since it pass original string in the recursive loop
    // which will set the original string as false in memo. But, Memo should be only used to store the "substring" status.
    public static void dfs1(String s, List<String> wordDict, List<List<String>> res,
                           int start, List<String> track) {
        if (start == s.length()) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String next = s.substring(start, i + 1); // start, i + 1
            if (wordDict.contains(next)) {
                track.add(next);
                dfs1(s, wordDict, res, i + 1, track);
                track.remove(track.size()-1);
            }
        }
    }
}
