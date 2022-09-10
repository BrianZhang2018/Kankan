package category.DFSBacktracing;

import java.util.*;

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

    public static void backtrack(List<String> list, String str, int left, int right, int n) {
        if (right == n) {
            list.add(str);
            return;
        }

        // str + ")" is alternative way of "sb.append(x); backtrack(...); sb.deleteCharAt(sb.size()-1)"
        if (left < n) backtrack(list, str + "(", left + 1, right, n);

        if (right < left) backtrack(list, str + ")", left, right + 1, n);
    }
}

// After line 6 which is "((()))", it will start returning to its caller function.
// So it will be "((())" --> "((()" --> "(((" in 2nd if block where we add/remove close bracket.
// Then it will return to 1st if block where we add open bracket as we came here from 1st if block.
// So here it will transition from "(((" --> "((" and then it will go to 2nd if block as its present next in line of execution.
// Now as number of close brackets are less than open bracket, it will add one close bracket and continue further with execution.

/*
time complexity:

I think it should be O(2^(2n)). Space is O(n). We need n pairs which means we need n left parenthesis and n right parenthesis, making it total 2n.
Then we need to decide will we include left or right? Think about the subset problem. We need to answer it 2^(2n) times.
Some people may argue it O(2^n), I would say the power 2 here is very significant and should not be ignored.
*/
/*
    (
    ((
    (((
    ((()
    ((())
    ((()))
    (()
    (()(
    (()()
    (()())
    (())
    (())(
    (())()
    ()
    ()(
    ()((
    ()(()
    ()(())
    ()()
    ()()(
    ()()()
*/
