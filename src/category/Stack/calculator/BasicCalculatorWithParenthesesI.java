package category.Stack.calculator;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 *
 * expression: parentheses + ('+', '-') + ' '
 *
 * Created by brianzhang on 11/17/19.
 */
public class BasicCalculatorWithParenthesesI {

    public static void main(String[] args) {
        System.out.println(calculate("2-(5-6)"));
    }

    public static int calculate(String s) {
        if(s == null) return 0;

        int result = 0,num = 0;
        int prevSign = 1; // means '+'

        Stack<Integer> signStack = new Stack<>(); // 解题思路, signStack
        signStack.push(prevSign);

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if(c == '+' || c == '-') {
                result += prevSign * num;
                prevSign = signStack.peek() * (c == '+' ? 1: -1); // 解题思路: prevSign transformed here
                // e.g. "2-(5-6)" will be converted to "2-5+6" as signStack.peek store the sign out of parentheses
                num = 0; // reset num after the sign
            } else if(c == '(') {   // store the sign right before the "(....)"
                signStack.push(prevSign);
            } else if(c == ')') {   // pop the sign right before the "(....)"
                signStack.pop();
            }
        }

        result += prevSign * num;
        return result;
    }
}
