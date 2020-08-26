package category.Stack;

import java.util.Stack;

/**
 * https://www.lintcode.com/problem/basic-calculator-iii/description
 * 这个方法也可以解 BasicCalculatorWithParenthesesI
 * <p>
 * Created by brianzhang on 2/23/20.
 */
public class BasicCalculatorWithParenthesesII {

    public static void main(String[] args) {
        System.out.println(calculate("6-4 / 2"));
    }

    public static int calculate(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int prevSign = '+';
        int m = s.length();
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        for (int i = 0; i < m; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            } else if (s.charAt(i) == '(') {
                int j = i, cnt = 0;
                for (; i < m; i++) {
                    if (s.charAt(i) == '(') cnt++;
                    if (s.charAt(i) == ')') cnt--;
                    if (cnt == 0) break;
                }
                //solve the parentheses recursively
                num = calculate(s.substring(j + 1, i));
            }

            if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == m - 1) {
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
        for (int i : stack) {
            res += i;
        }

        return res;
    }
}
