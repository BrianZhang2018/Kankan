package category.Stack.calculator;

import java.util.Stack;

/**
 * https://www.lintcode.com/problem/basic-calculator-iii
 *
 * expression: parentheses + (+, -, *, /) + ' '
 *
 * 这个和BasicCalculatorII的 code structure很像，可以抽取为模板算法，
 *
 * Created by brianzhang on 2/23/20.
 */
public class BasicCalculatorWithParenthesesIII {

    public static void main(String[] args) {
        System.out.println(calculate("6-(1+(4/2))"));
    }

    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        char prevSign = '+';
        String signs = "+-*/";
        int m = s.length();
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                int start = i, cnt = 0;
                // find the "(.(...).)"
                for (; i < m; i++) {
                    if (s.charAt(i) == '(') cnt++;
                    if (s.charAt(i) == ')') cnt--;
                    if (cnt == 0) break;
                }
                // calculate the found parentheses expression "Recursively"
                num = calculate(s.substring(start + 1, i));
            }

            // 解题思路： common way to calculate the number before the current sign
            if (i == m - 1 || (signs.indexOf(c)!=-1)) {
                if (prevSign == '+') {
                    stack.push(num);
                } else if (prevSign == '-') {
                    stack.push(-num);
                } else if (prevSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (prevSign == '/') {
                    stack.push(stack.pop() / num);
                }
                prevSign = s.charAt(i);
                num = 0;
            }
        }

        int res = 0;
        for (int i : stack) res += i;

        return res;
    }
}
