package category.Stack.calculator;

import java.util.Stack;
/**
 * https://leetcode.com/problems/basic-calculator-iii/
 *
 * Operations include: (), +, -, *, /, and ' '
 * 这个和BasicCalculatorII的 code structure很像，可以抽取为模板算法，
 *
 * Created by brianzhang on 2/23/20.
 */
public class BasicCalculatorAllOpsWithParentheses {
    public static void main(String[] args) {
/*        System.out.println(calculate("6-(1+(4/2))"));
        System.out.println(calculate1("6-(1+(4/2))"));*/
     /*   System.out.println(calculate("(5*(4-(3*2)))"));
        System.out.println(calculate1("(5*(4-(3*2)))"));*/
        System.out.println(calculate("2+5-6+1"));
    }

// Better Solution - without stack, how to track the progress: 1. global index 2. queue. Same approach in "decodeString" problem
// Can be used for all the calculator problems.
// e.g. 2 + 3 - 4 (+)
//        + [sum += prevNum(0), then prevNum = 2, prevOps= '+']
//            - [ sum += prevNum(2), then prevNum = +3, prevOps= '-']
//                 + [sum += prevNum(3), then prevNum = -4]
    static int index = 0;
    public static int calculate(String s) {
        char prevOps = '+';
        int num = 0, sum = 0, prevNum = 0;
        String ops = "+-/*";
        while (index < s.length()) {
            char c = s.charAt(index++);
            if ('0' <= c && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                num = calculate(s); // recursive
            }
            // the below section is mainly for calculating the new prevNum, the secondary is to sum the old prevNum
            if(ops.indexOf(c) != -1 || c == ')' || index == s.length()) { // index == s.length(): faciliates to computer the last number
                switch (prevOps) { // prevOps is used to determine the new prevNum
                    case '+':
                        sum += prevNum; // do the calculation
                        prevNum = num; // prevNum base on: 1. prevOps 2. num
                        break;
                    case '-':
                        sum += prevNum;
                        prevNum = -num; // note: -num
                        break;
                    case '*':
                        prevNum *= num; // "*" and "/" ops is calculated firstly, then sum.
                        break;
                    case '/':
                        prevNum /= num;
                        break;
                }
                if (c == ')') {
                    break;
                }
                prevOps = c;
                num = 0;
            }
        }

        return sum + prevNum;
    }

// Template solution with stack
    public static int calculate1(String s) {
        if (s == null || s.length() == 0) return 0;

        char prevOps = '+';
        String ops = "+-*/";
        int m = s.length();
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                int start = i, cnt = 0;
                // find end index of rightmost outer parenthesis "(.(...).)"
                while (i < m) {
                    if (s.charAt(i) == '(') cnt++;
                    if (s.charAt(i) == ')') cnt--;
                    if (cnt == 0) break;
                    i++;
                }
                num = calculate1(s.substring(start + 1, i)); // take the sub string inside of parenthesis ( ... )
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

        return stack.stream().reduce(0, Integer::sum); // return stack.stream().reduce(Integer::sum).get();
    }
}
