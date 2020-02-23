package category.Stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 *
 * only has the '+' and '-' operation
 *
 * Created by brianzhang on 11/17/19.
 */
public class BasicCalculatorWithParenthesesI {

    public static void main(String[] args) {
        System.out.println(calculate("2-(5-6)"));
    }

    public static int calculate(String s) {
        if(s == null) return 0;

        int result = 0;
        int prevSign = 1;
        int num = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(prevSign);

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(Character.isDigit(c)) {
                num = num * 10 + (c - '0');

            } else if(c == '+' || c == '-') {
                result += prevSign * num;
                prevSign = stack.peek() * (c == '+' ? 1: -1); // 2-(5-6) will be converted to 2-5+6 by this line (精华)
                num = 0;

            } else if(c == '(') {
                stack.push(prevSign);

            } else if(c == ')') {
                stack.pop();

            }
        }

        result += prevSign * num;
        return result;
    }

    public int calculate1(String s) {
        int len = s.length(), sign = 1, result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int digit = s.charAt(i) - '0';
                while (i+1 < len && Character.isDigit(s.charAt(i + 1))) {
                    digit = digit * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += digit * sign;
            } else if (s.charAt(i) == '+')
                sign = 1;
            else if (s.charAt(i) == '-')
                sign = -1;
            else if (s.charAt(i) == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                result = result * stack.pop() + stack.pop();
            }

        }
        return result;
    }
}
