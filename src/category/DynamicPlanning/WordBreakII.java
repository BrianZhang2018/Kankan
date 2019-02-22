package category.DynamicPlanning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brianzhang on 2/20/19.
 */
public class WordBreakII {


    public List<String> wordBreak(String s, List<String> wordDict) {

        if (s == null || s.length() == 0)
            return null;

        return dfs(s, 0, new HashMap<Integer, List<String>>(), wordDict);
    }

    private List<String> dfs(String s, int index, Map<Integer, List<String>> mem, List<String> dict) {

        if (mem.get(index) != null)
            return mem.get(index);

        List<String> currRes = new ArrayList();
        String curr = s.substring(index);
        if (index == s.length()) {
            currRes.add("");
            return currRes;
        }

        for (String word : dict) {
            if (curr.startsWith(word)) {
                List<String> subRes = dfs(s, index + word.length(), mem, dict);
                for (String wordsStr : subRes) {
                    currRes.add(word + (wordsStr.isEmpty() ? "" : " ") + wordsStr);
                }
            }
        }

        mem.put(index, currRes);
        return currRes;
    }
}
