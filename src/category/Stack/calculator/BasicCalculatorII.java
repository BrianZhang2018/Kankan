package category.Stack.calculator;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 *
 * expression: (+, -, *, /) + ' ', but no parentheses
 *
 * store the calculated the number into a stack, then sum the value in stack and return
 *
 * Created by brianzhang on 11/16/19.
 */
public class BasicCalculatorII {
    public static void main(String[] args) {
        System.out.println(calculate(" 3/2 "));
    }

    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        Stack<Integer> stack = new Stack<>();
        String signs = "+-*/";
        char prevSign = '+'; //
        int tmpNum = 0; // the number between the sign

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) { // accumulate the number
                tmpNum = tmpNum * 10 + s.charAt(i) - '0'; //sum up the multi-digit number e.g. "23" -> 2*10+3
            }

            // Not "else if" here as we need calculate the last number, common way to calculate the number
            if (i == s.length() - 1 || (signs.indexOf(c)!=-1)) { // 当我们遇到一个新的sign时，要把前面的部分计算掉(prevSign + number).
                if (prevSign == '-') {
                    stack.push(-tmpNum);
                } else if (prevSign == '+') {
                    stack.push(tmpNum);
                } else if (prevSign == '*') {
                    stack.push(stack.pop() * tmpNum);
                } else if (prevSign == '/') {
                    stack.push(stack.pop() / tmpNum);
                }
                prevSign = c;
                tmpNum = 0;
            }
        }

        int res = 0;
        for (int i : stack) res += i;

        return res;
    }
}
