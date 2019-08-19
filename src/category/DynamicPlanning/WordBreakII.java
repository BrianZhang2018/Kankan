package category.DynamicPlanning;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break-ii/
 * 
 * this should be a backtracking solution :)
 * Created by brianzhang on 2/20/19.
 */
public class WordBreakII {

    public static void main(String[] args) {
        WordBreakII wordBreakII = new WordBreakII();
        List<String> dicts = new ArrayList<>();
        dicts.add("cat");
        dicts.add("cats");
        dicts.add("and");
        dicts.add("sand");
        dicts.add("dog");
       for(String str : wordBreakII.wordBreak1("catsanddog", dicts))
           System.out.println(str);

    }

    public List<String> wordBreak1(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, List<String>>());
    }

    // DFS function returns an array including all substrings derived from s - backtracking
    List<String> DFS(String s, List<String> wordDict, HashMap<String, List<String>>map) {
        if (map.containsKey(s))
            return map.get(s);

        List<String> res = new ArrayList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }
}
