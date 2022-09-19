package category.Stack.calculator;

import java.util.*;

/**
 * https://www.lintcode.com/problem/basic-calculator-iii
 *
 * Operations include: (), +, -, *, / and ' '
 * 这个和BasicCalculatorII的 code structure很像，可以抽取为模板算法，
 *
 * Created by brianzhang on 2/23/20.
 */
public class BasicCalculatorParenthesesAllOps {
    public static void main(String[] args) {
        System.out.println(calculate("(4/2)+1"));
        System.out.println(calculate("6-(1+(4/2))"));
        System.out.println(calculate1("6-(1+(4/2))"));
        System.out.println(calculate("(5*(4-(3*2)))"));
        System.out.println(calculate1("(5*(4-(3*2)))"));
    }

    // template solution
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        char prevOps = '+';
        String ops = "+-*/";
        int m = s.length();
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0'; //
            } else if (c == '(') {
                int start = i, cnt = 0;
                // find end index of rightmost outer parenthesis "(.(...).)"
                while (i < m) {
                    if (s.charAt(i) == '(') cnt++;
                    if (s.charAt(i) == ')') cnt--;
                    if (cnt == 0) break;
                    i++;
                }
                num = calculate(s.substring(start + 1, i)); // take the sub string inside of parenthesis ( ... )
            }

            if (ops.indexOf(c) != -1 || i == m - 1) {  // template: calculate the number before the current sign
                if (prevOps == '+') {
                    stack.push(num);
                } else if (prevOps == '-') {
                    stack.push(-num);
                } else if (prevOps == '*') {
                    stack.push(stack.pop() * num);
                } else if (prevOps == '/') {
                    stack.push(stack.pop() / num);
                }
                prevOps = c;
                num = 0;
            }
        }

        return stack.stream().reduce(0, Integer::sum);
       // return stack.stream().reduce(Integer::sum).get();
    }

// Solution without stack
    public static int calculate1(String s) {
        Queue<Character> tokens = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') tokens.offer(c);
        }
        tokens.offer('+');
        return calculate1(tokens);
    }

    // e.g. 2 + 3 * 4 (+)
    //        ^ (sum + prev = 0, prev = 2)
    //            ^ ( (2+) sum prev number, then prevNum = currentNum = 3)
    //                 ^ ((3*4) do the calculation, then prevNum = 12)
    private static int calculate1(Queue<Character> tokens) {
        char preOps = '+';
        int num = 0, sum = 0, prevNum = 0; // additional prevNum required here compared with above solution
        while (!tokens.isEmpty()) {
            char c = tokens.poll();
            if ('0' <= c && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                num = calculate1(tokens); // recursive
            } else {
                switch (preOps) {
                    case '+':
                        sum += prevNum; // both "+" and "-" cases are to accumulate the value, both "+" prev
                        prevNum = num;
                        break;
                    case '-':
                        sum += prevNum; // ^^
                        prevNum = -num;
                        break;
                    case '*':
                        prevNum *= num; // since "*" and "/" ops are need to be calculated, then accumulate. so no sum here, but accumulated in above "+" and "-" case
                        break;
                    case '/':
                        prevNum /= num;
                        break;
                }
                if (c == ')') {
                    break;
                }
                preOps = c;
                num = 0;
            }
        }

        return sum + prevNum;
    }
}
