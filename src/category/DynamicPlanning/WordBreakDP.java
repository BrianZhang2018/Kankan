package category.DynamicPlanning;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by brianzhang on 1/9/19.
 */
public class WordBreakDP {

    public static void main(String[] args) {
        //solution-1
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("leet");
        hashSet.add("co");
        System.out.println(wordBreak("leetco", hashSet));

        //solution-2
        System.out.println(wordBreakDPMem("leetco", hashSet, new HashMap<String, Boolean>()));
    }

    //递推
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

    //递归
    public static boolean wordBreakDPMem(String s, Set<String> dict, Map<String, Boolean> mem) {
        if (mem.containsKey(s))
            return mem.get(s);

        if (dict.contains(s)) {
            mem.put(s, true);
            return true;
        }

        // ++i vs i++
        for (int i = 1; i < s.length(); i++) {
            if (dict.contains(s.substring(i)) && wordBreakDPMem(s.substring(0, i), dict, mem)) {
                mem.put(s, true);
                return true;
            }
        }

        mem.put(s, false);
        return false;
    }
}