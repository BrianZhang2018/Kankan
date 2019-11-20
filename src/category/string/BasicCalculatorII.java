package category.string;

import java.util.Stack;

/**
 * Medium
 * https://leetcode.com/problems/basic-calculator-ii/
 *
 * Created by brianzhang on 11/16/19.
 */
public class BasicCalculatorII {
    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
    }

    public static int calculate(String s) {
        if(s==null || s.length() == 0) return 0;

        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        char prevSign = '+';
        int num = 0;

        for(int i=0;i<len;i++){
            if(Character.isDigit(s.charAt(i))){
                num = num * 10 + s.charAt(i)-'0';
            }
            if((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i==len-1){
                if(prevSign=='-'){
                    stack.push(-num);
                } else if(prevSign=='+'){
                    stack.push(num);
                } else if(prevSign=='*'){
                    stack.push(stack.pop() * num);
                } else if(prevSign=='/'){
                    stack.push(stack.pop() / num);
                }
                prevSign = s.charAt(i);
                num = 0;
            }
        }

        int res = 0;
        for(int i:stack){
            res += i;
        }
        return res;
    }

}
