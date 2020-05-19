package category.BacktracingDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 *
 * Created by brianzhang on 8/5/18.
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        generateParenthesis(3);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    // note: str rather than the StringBuilder, and pass new reference (e.g. str + ")") into next loop.
    // (don't have the remove action for this problem, so can't pass a reference type to next loop)
    public static void backtrack(List<String> list, String str, int open, int close, int max) {

        if (str.length() == max * 2) {
            list.add(str); System.out.println(str);
            return;
        }

        //divide and conquer idea (traverse和divide conquer 都属于DFS)
        if (open < max)
            backtrack(list, str + "(", open + 1, close, max);
        if (close < open)
            backtrack(list, str + ")", open, close + 1, max);
    }
}
