package category.Stack.calculator;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 *
 * Operations includes: +, -, *, / and ' ', but "No Parentheses"
 * prevSign + Stack
 *
 * Created by brianzhang on 11/16/19.
 */
public class BasicCalculatorAllOpsNoParentheses {
    public static void main(String[] args) {
        System.out.println(calculate("15-2*2"));
        System.out.println(calculate(" 3/2 "));
       // System.out.println(calculate("1 + 3 * 2 "));
    }

    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>(); // store calculation result
        String signs = "+-*/";
        char prevOps = '+';
        int num = 0; // the number between the sign

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) { // accumulate the number
                num = num * 10 + s.charAt(i) - '0'; //sum up the multi-digit number e.g. "23" -> 2*10+3
                if (i != s.length() - 1) continue; // can omit
            }
            // Catch: not "else if" here as we need compute the "last number", below is the template way to calculate the number with ops
            if (i == s.length() - 1 || (signs.indexOf(c) != -1)) { // 解题思路: 当我们遇到一个Ops时，要把前面的部分计算掉(prevOps "+-*/" number)
                if (prevOps == '-') {
                    stack.push(-num);
                } else if (prevOps == '+') {
                    stack.push(num);
                } else if (prevOps == '*') {
                    stack.push(stack.pop() * num);
                } else if (prevOps == '/') {
                    stack.push(stack.pop() / num);
                }

                prevOps = c;
                num = 0;
            }
        }

        return stack.stream().reduce(Integer::sum).get();
    }
}
