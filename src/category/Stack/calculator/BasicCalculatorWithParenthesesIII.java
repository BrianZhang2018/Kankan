package category.Stack.calculator;

import java.util.*;

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
        System.out.println(calculate1("6-(1+(4/2))"));
        System.out.println(calculate("(5*(4-(3*2)))"));
        System.out.println(calculate1("(5*(4-(3*2)))")); // calculate1 has problem with this case
    }

    // template solution
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        char prevSign = '+';
        String signs = "+-*/";
        int m = s.length();
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            else if (c == '(') {
                int start = i, cnt = 0;
                // find end index of rightmost outer parenthesis "(.(...).)"
                for (; i < m; i++) {
                    if (s.charAt(i) == '(') cnt++;
                    if (s.charAt(i) == ')') cnt--;
                    if (cnt == 0) break;
                }
                // recursive
                num = calculate(s.substring(start + 1, i)); // take the string inside of parenthesis ( ... )
            }

            if (signs.indexOf(c)!=-1 || i==m-1) {  // template: calculate the number before the current sign
                if (prevSign == '+') {
                    stack.push(num);
                } else if (prevSign == '-') {
                    stack.push(-num);
                } else if (prevSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (prevSign == '/') {
                    stack.push(stack.pop() / num);
                }
                prevSign = c;
                num = 0;
            }
        }

        int res = 0;
        for (int val : stack) res += val;
        return res;
    }

    // much better performance solution without stack
    static int i = 0; // can use queue track the remaining elements
    public static int calculate1(String s) {
        int sum = 0;    // use "sum" replace stack in above solution
        char prevSign = '+';
        int num = 0;
        int prev = 0;
        while (i < s.length()) {
            char ch = s.charAt(i++);
            if (ch == '(') {
                num = calculate1(s);
            }
            else if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            if (i == s.length() || (ch != '(' && !Character.isDigit(ch))) {
                if (prevSign == '+') {
                    sum += prev;
                    prev = num;
                } else if (prevSign == '-') {
                    sum += prev;    // be careful here is '+', not '-' since prev has already has the sign
                    prev = -num;
                } else if (prevSign == '*') {
                    prev *= num;
                } else if (prevSign == '/') {
                    prev /= num;
                }
                prevSign = ch;
                num = 0;
            }
            if (ch == ')') {
                break;
            }
        }
        return sum + prev;
    }


}
