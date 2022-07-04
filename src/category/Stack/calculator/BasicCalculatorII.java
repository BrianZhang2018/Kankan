package category.Stack.calculator;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 * expression: +, -, *, / and ' ', but no parentheses
 * prevSign + Stack
 *
 * Created by brianzhang on 11/16/19.
 */
public class BasicCalculatorII {
    public static void main(String[] args) {
       // System.out.println(calculate("3-2*2"));
        System.out.println(calculate1(" 3/2 "));
       // System.out.println(calculate("1 + 3 * 2 "));
    }

    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>(); // store calculation result
        String signs = "+-*/";
        char prevSign = '+';
        int num = 0; // the number between the sign

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) { // accumulate the number
                num = num * 10 + s.charAt(i) - '0'; //sum up the multi-digit number e.g. "23" -> 2*10+3
            }
            //Note: not "else if" here as we need calculate the "last number", below is the template way to calculate the number
            if (i == s.length() - 1 || (signs.indexOf(c)!=-1)) { // 解题思路: 当我们遇到一个新的sign时，要把前面的部分计算掉(prevSign + number)
                if (prevSign == '-') {
                    stack.push(-num);
                } else if (prevSign == '+') {
                    stack.push(num);
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
        for (int i : stack) res += i;
        return res;
    }

    public static int calculate1(String s) {
        Stack<Integer> stack = new Stack();
        String signs = "+-*/";
        char prev = '+';
        int num = 0;
        char[] cs = s.toCharArray();

        int res = 0;

        for(int i=0; i<cs.length; i++) {
            if(Character.isDigit(cs[i])) {
                num += num *10 + cs[i] - '0';
            }

            if(i == cs.length-1 || signs.indexOf(cs[i]) != -1) {
                if(prev == '+') {
                    stack.push(num);
                }else if(prev == '-') {
                    stack.push(-num);
                }else if (prev == '*') {
                    stack.push(stack.pop() * num);
                }else if(prev == '/') {
                    stack.push(stack.pop() / num);
                }
                prev = cs[i];
                num = 0;
            }
        }

        for(int i : stack) {
            res +=i;
        }

        return res;
    }
}
