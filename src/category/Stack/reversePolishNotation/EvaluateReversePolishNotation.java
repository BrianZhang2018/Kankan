package category.Stack.reversePolishNotation;

import java.util.*;
/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * https://en.wikipedia.org/wiki/Reverse_Polish_notation
 *
 * e.g.
 * ["2", "1", "+", "3", "*"]
 * ((2 + 1) * 3) = 9
 *
 * Created by brianzhang on 5/16/20.
 */
public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
/*        System.out.println(evalRPN(new String[] {"2", "1", "+", "3", "*"}));
        System.out.println(isValidRPN(new String[] {"2", "1", "+", "3", "*"}));
        System.out.println(isValidRPN(new String[] {"2", "1", "+", "3", "*", "*"}));*/

        TreeMap<Integer, Integer> tm = new TreeMap();
        tm.put(1,1);

        System.out.println(tm.put(2,2));

    }

    public static int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length ==0) return 0;

        String operators = "+-*/";
        Stack<Integer> stack = new Stack<>();

        for(String str : tokens){
            if(operators.contains(str)){
                int num2 = stack.pop(); // be careful here
                int num1 = stack.pop();

                switch (str) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2); // has to be num1-num2
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2); // has to be num1/num2
                        break;
                }
            }else{
                stack.push(Integer.valueOf(str));
            }
        }

        return stack.pop();
    }

    // Follow-up question: how to check whether it's a valid RPN
    // https://stackoverflow.com/questions/14506831/whats-the-fastest-way-to-check-if-input-string-is-a-correct-rpn-expression
    public static boolean isValidRPN(String[] tokens) {
        int count = 0;
        String operators = "+-*/";
        for(String s : tokens){
            if(operators.contains(s)) {
                count--;
            }else {
                count++;
            }
            if (count <=0) return false;
        }
        return count == 1 ? true : false;
    }
/**
    expression:     3 4 + 1 * _
    stack_size:   0 1 2 1 2 1 1 -> success

    expression:     2 3 4 + 1 * _
    stack_size:   0 1 2 3 2 3 2 2 -> failure (not 1 at the end)

    expression:     2 3 + + 1 * _
    stack_size:   0 1 2 1 0       -> failure (stack_size <= 0)
 **/
}
