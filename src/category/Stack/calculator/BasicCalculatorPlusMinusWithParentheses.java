package category.Stack.calculator;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 * Operation includes: (), '+', '-', allow empty ' '
 *
 * Created by brianzhang on 11/17/19.
 */
public class BasicCalculatorPlusMinusWithParentheses {
    public static void main(String[] args) {
        //System.out.println(calculate("2-(5-6)"));
        System.out.println(calculate( "- (3 + (4 + 5))"));
    }
    public static int calculate(String s) {
        if(s == null) return 0;
        int sum = 0;
        int num = 0;
        int ops = 1; // means sign '+'
        Stack<Integer> opsStack = new Stack<>(); // key: opsStack store the ops before the bracket '('
        opsStack.push(ops);
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if(c == '+' || c == '-') {
                sum += ops * num;
                // tricky: use the preOps to transform the value in bracket
                // e.g. "2-(5-6)" will be converted to "2-5+6" as opsStack.peek store the ops before the parentheses
                ops = opsStack.peek() * (c == '+' ? 1: -1);
                num = 0; // reset num
            } else if(c == '(') { // store the ops before current "(....)"
                opsStack.push(ops);
            } else if(c == ')') { // remove the ops before current "(....)"
                opsStack.pop();
            }
        }

        return sum + ops * num;
    }
}
