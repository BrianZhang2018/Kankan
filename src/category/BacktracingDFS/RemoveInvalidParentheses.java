package category.BacktracingDFS;

import java.util.*;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75034/Easiest-9ms-Java-Solution

    1. Limit max removal rmL and rmR for backtracking boundary. Otherwise it will exhaust all possible valid substrings, not shortest ones.
    2. Scan from left to right, avoiding invalid strs (on the fly) by checking num of open parens.
    3. If it's '(', either use it, or remove it.
    4. If it's ')', either use it, or remove it.
    5. Otherwise just append it.
    6. Lastly set StringBuilder to the last decision point.

 *  Backtracking solution
 * Created by brianzhang on 4/5/20.
 */
public class RemoveInvalidParentheses {

    public static void main(String[] args) {
        RemoveInvalidParentheses rp = new RemoveInvalidParentheses();
        for(String str : rp.removeInvalidParentheses("(a)())()")) System.out.println(str);
    }

    public List<String> removeInvalidParentheses(String s) {
        int rmL = 0, rmR = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rmL++;
            } else if (s.charAt(i) == ')') {
                if (rmL != 0) {
                    rmL--;
                } else {
                    rmR++;
                }
            }
        }
        Set<String> res = new HashSet<>();
        dfs(s, 0, res, new StringBuilder(), rmL, rmR, 0);
        return new ArrayList<>(res);
    }

    public void dfs(String s, int i, Set<String> res, StringBuilder tempSb, int rmL, int rmR, int open) {
        if (rmL < 0 || rmR < 0 || open < 0) {
            return;
        }
        if (i == s.length()) {
            if (rmL == 0 && rmR == 0 && open == 0) {
                res.add(tempSb.toString());
            }
            return;
        }

        char c = s.charAt(i);

        if (c == '(') {
            dfs(s, i + 1, res, tempSb, rmL - 1, rmR, open);		    // not use (
            dfs(s, i + 1, res, tempSb.append(c), rmL, rmR, open + 1); // use (
        } else if (c == ')') {
            dfs(s, i + 1, res, tempSb, rmL, rmR - 1, open);	        // not use  )
            dfs(s, i + 1, res, tempSb.append(c), rmL, rmR, open - 1); // use )
        } else {
            dfs(s, i + 1, res, tempSb.append(c), rmL, rmR, open);
        }

        tempSb.setLength(tempSb.length() - 1); // backtrack
    }
}
