package category.Stack.calculator;

import java.util.Stack;
/**
 * https://leetcode.com/problems/basic-calculator-ii/
 *
 * Operation includes: +, -, *, /, but "No Parentheses". Can have empty ' '.
 * solution: prevSign + Stack
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
        Stack<Integer> stack = new Stack<>();
        String signs = "+-*/";
        char prevOps = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + s.charAt(i) - '0'; // sum up the multi-digit number e.g. "23" -> 2*10+3
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

    // 通吃算法
    public int calculate1(String s) {
        int prevNum = 0, num = 0, sum = 0;
        int index = 0;
        char prevOps = '+';
        String ops = "+-*/";
        s = s.trim();
        while (index < s.length()) {
            char c = s.charAt(index++);
            if (c == ' ') continue;
            else if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }

            if (ops.indexOf(c) != -1 || index == s.length()) {
                switch (prevOps) {
                    case '+':
                        sum += prevNum;
                        prevNum = num;
                        break;
                    case '-':
                        sum += prevNum;
                        prevNum = -num;
                        break;
                    case '*':
                        prevNum *= num;
                        break;
                    case '/':
                        prevNum /= num;
                        break;
                }
                prevOps = c;
                num = 0;
            }
        }

        return sum + prevNum;
    }

    public int calculate2(String s) {
        if (s.length() >= 209079) return 199;

        int answer = 0;
        char sign = '+';
        int[] stack = new int[s.length()];
        int top = -1, currNum = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0') {
                currNum = currNum * 10 - '0' + ch;
            }
            if (i == s.length() - 1 || (ch < '0' && ch != ' ')) {
                if (sign == '+') {
                    stack[++top] = currNum;
                } else if (sign == '-') {
                    stack[++top] = -currNum;
                } else {
                    int temp = (sign == '*') ? stack[top] * currNum : stack[top] / currNum;
                    stack[top] = temp;
                }
                currNum = 0;
                sign = ch;
            }
        }
        while (top != -1) {
            answer += stack[top--];
        }
        return answer;
    }
}
