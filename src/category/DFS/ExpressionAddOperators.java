package category.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/expression-add-operators/
 *
 * When we use dfs to do this question, the most tricky part is that how to deal with multiplication.
 * For every addition and subtraction, we just directly adding or subtracting the new number.
 * However, for multiplication, we should multiply current number and previous number firstly,
 * and then add previous previous number. So we can use a variable preNum to record every previous number in each recursion step.
 * If current recursive call is trying multiplication, we should use previous calculation value subtract previous number,
 * and then adding multiplication result between previous number and current number.
 *
 * https://leetcode.com/problems/expression-add-operators/discuss/71895/Java-Standard-Backtrace-AC-Solutoin-short-and-clear
 * Created by brianzhang on 3/17/19.
 */
public class ExpressionAddOperators {
    public static void main(String[] args) {
        ExpressionAddOperators test = new ExpressionAddOperators();
        System.out.println(test.addOperators("103", 6));
    }


    public List<String> addOperators(String num, int target) {
        if (num.length() == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        dfs(result, num, target, "", 0, 0, 0);
        return result;
    }

    /**
     * @param result:               result list to store valid expressions
     * @param num:                  input num candidates
     * @param target:               input target number
     * @param expr:                 current expression string
     * @param calcVal:              current calculation value
     * @param preMultiplicationNum: previous multiplication number, e.g 1+2*3*4, the
     *                              first preMultiplicationNum is 2, and the next is 2*3
     * @param pos:                  current index in the input num array
     */
    public void dfs(List<String> result, String num, int target, String expr, long calcVal, long preMultiplicationNum,
            int pos) {
        if (pos == num.length()) {
            if (calcVal == target) {
                result.add(expr);
            }
            return;
        }

        // start from first index of current position in num string, try all possible length of nums
        for (int i = pos; i < num.length(); i++) {
            // corner case: if current position's digit is 0, we can only use it as a single digit number which is 0
            // if it is not a single digit number with leading 0, it's an invalid number
            if (i != pos && num.charAt(pos) == '0') { // check leading 0
                break;
            }
            long curNum = Long.parseLong(num.substring(pos, i + 1));

            // position 0 should be considered individually, since it does not have any operand character before curNum
            if (pos == 0) {
                dfs(result, num, target, expr + curNum, curNum, curNum, i + 1);
            } else {
                dfs(result, num, target, expr + "+" + curNum, calcVal + curNum, curNum, i + 1);
                dfs(result, num, target, expr + "-" + curNum, calcVal - curNum, -curNum, i + 1);
                // trick part: to calculate multiplication, we should subtract previous number, and then add current multiplication result
                // also, put the current multiplication result as preMultiplicationNum
                dfs(result, num, target, expr + "*" + curNum,
                        calcVal - preMultiplicationNum + preMultiplicationNum * curNum, preMultiplicationNum * curNum,
                        i + 1);
            }
        }
    }
}
