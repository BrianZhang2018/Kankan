package category.Stack.calculator;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 *
 * Operations includes: (), '+', '-' and ' '
 *
 * Created by brianzhang on 11/17/19.
 */
public class BasicCalculatorParenthesesPlusDivide {
    public static void main(String[] args) {
        System.out.println(calculate("2-(5-6)"));
    }

    public static int calculate(String s) {
        if(s == null) return 0;

        int result = 0;
        int num = 0;
        int prevOps = 1; // means sign '+'
        Stack<Integer> opsStack = new Stack<>(); // 解题思路: opsStack
        opsStack.push(prevOps);
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if(c == '+' || c == '-') {
                result += prevOps * num;
                // 解题思路: preOps is transformed here
                // e.g. "2-(5-6)" will be converted to "2-5+6" as opsStack.peek store the ops before the parentheses
                prevOps = opsStack.peek() * (c == '+' ? 1: -1);
                num = 0; // reset num
            } else if(c == '(') {   // store the ops before current "(....)"
                opsStack.push(prevOps);
            } else if(c == ')') {   // remove the ops before current "(....)"
                opsStack.pop();
            }
        }

        result += prevOps * num;
        return result;
    }
}
