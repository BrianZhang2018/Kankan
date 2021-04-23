package category.Stack.calculator;

import java.util.Stack;

/**
 * https://www.lintcode.com/problem/basic-calculator-iii
 *
 * Expression: (), +, -, *, / and ' '
 * 这个和BasicCalculatorII的 code structure很像，可以抽取为模板算法，
 *
 * Created by brianzhang on 2/23/20.
 */
public class BasicCalculatorWithParenthesesIII {

    public static void main(String[] args) {
        System.out.println(calculate("6-(1+(4/2))"));
    }

    // Recursively calculate the nested expression
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
                // find end index of rightmost outer parenthesis "(.(...).)"
                for (; i < m; i++) {
                    if (s.charAt(i) == '(') cnt++;
                    if (s.charAt(i) == ')') cnt--;
                    if (cnt == 0) break;
                }
                // recursive
                num = calculate(s.substring(start + 1, i)); // take the string inside of ( ... )
            }

            // 解题思路：the template of calculate the number before the current sign
            if (i == m - 1 || signs.indexOf(c)!=-1) {
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
