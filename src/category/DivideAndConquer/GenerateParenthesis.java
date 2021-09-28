package category.DivideAndConquer;

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
        dfs(list, "", 0, 0, n);
        return list;
    }

    //  str + ")" will create a new string and pass into next loop.
    //  don't have the remove action here, so can't pass same string to next loop
    public static void dfs(List<String> list, String str, int open, int close, int max) {

        if (str.length() == max * 2) {
            list.add(str); System.out.println(str);
            return;
        }

        // divide and conquer
        if (open < max)
            dfs(list, str + "(", open + 1, close, max);
        if (close < open)
            dfs(list, str + ")", open, close + 1, max);
    }
}
