package category.Stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Created by brianzhang on 5/16/20.
 */
public class EvaluateReversePolishNotation {

    public static void main(String[] args) {

        System.out.println(evalRPN(new String[] {"2", "1", "+", "3", "*"}));
    }

    public static int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length ==0)
            return 0;

        String operators = "+-*/";
        Stack<Integer> stack = new Stack<>();

        for(String str : tokens){

            if(operators.contains(str)){
                int num2 = stack.pop(); // important here
                int num1 = stack.pop();

                switch (str) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                }
            }else{
                stack.push(Integer.valueOf(str));
            }
        }

        return stack.pop();
    }
}
