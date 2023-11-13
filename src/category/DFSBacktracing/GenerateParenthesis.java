package category.DFSBacktracing;

import java.util.*;

/**
 * https://leetcode.com/problems/generate-parentheses/
 *
 * Created by brianzhang on 8/5/18.
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    // solution-1: 与solution-2的区别就是str  + "(", 解决了remove character的问题，sb.deleteCharAt(sb.length()-1);
    public static void backtrack(List<String> list, String str, int leftCount, int rightCount, int n) {
        if (rightCount == n) {
            list.add(str);
            return;
        }

        // 两点要注意:
        // 1. str + "(", rather than sb.append("(")
        // 2. leftCount + 1, not leftCount++
        if (leftCount < n)
            backtrack(list, str + "(", leftCount + 1, rightCount, n);
        if (rightCount < leftCount)
            backtrack(list, str + ")", leftCount, rightCount + 1, n);
    }
}

/***
After "((()))", it will start returning to its caller function.
So it will be "((())" --> "((()" --> "(((" in 2nd if block where we add/remove close bracket.
Then it will return to 1st if block where we add open bracket as we came here from 1st if block.
So here it will transition from "(((" --> "((" and then it will go to 2nd if block as its present next in line of execution.
Now as number of close brackets are less than open bracket, it will add one close bracket and continue further with execution.
 */

/***
Time complexity:

I think it should be O(2^(2n)). Space is O(n). We need n pairs which means we need n left parenthesis and n right parenthesis, making it total 2n.
Then we need to decide will we include left or right? Think about the subset problem. We need to answer it 2^(2n) times.
Some people may argue it O(2^n), I would say the power 2 here is very significant and should not be ignored.
*/

/***
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
***/