package category.BacktracingDFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75034/Easiest-9ms-Java-Solution
 *
 * Created by brianzhang on 4/5/20.
 */
public class RemoveInvalidParentheses {

    public static void main(String[] args) {
        RemoveInvalidParentheses rp = new RemoveInvalidParentheses();
        for(String str : rp.removeInvalidParentheses("(a)())()"))
            System.out.println(str);

        StringBuilder sb = new StringBuilder("abc");
        sb.setLength(1);
        System.out.println(sb.toString()); // "a"

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
        int len = tempSb.length();

        if (c == '(') {
            dfs(s, i + 1, res, tempSb, rmL - 1, rmR, open);		    // not use (
            dfs(s, i + 1, res, tempSb.append(c), rmL, rmR, open + 1);       // use (

        } else if (c == ')') {
            dfs(s, i + 1, res, tempSb, rmL, rmR - 1, open);	            // not use  )
            dfs(s, i + 1, res, tempSb.append(c), rmL, rmR, open - 1);  	    // use )

        } else {
            dfs(s, i + 1, res, tempSb.append(c), rmL, rmR, open);
        }

        tempSb.setLength(len); //set StringBuilder to the last decision point.
    }
}
