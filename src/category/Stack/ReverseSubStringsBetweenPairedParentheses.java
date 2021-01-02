package category.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/discuss/382421/JavaPython-3-Iterative-short-codes-w-comment-and-analysis.
 * Created by brianzhang on 2/17/20.
 */
public class ReverseSubStringsBetweenPairedParentheses {

    public static void main(String[] args) {
        System.out.println(reverseParentheses("(ed(et(oc))el)"));
    }

    // Solution-1: iteration
    public static String reverseParentheses(String s) {
        Deque<StringBuilder> dq = new ArrayDeque<>();
        dq.push(new StringBuilder());

        for(char c : s.toCharArray()){
            if(c == '('){
                dq.push(new StringBuilder());
            }else if(c == ')'){
                StringBuilder str = dq.pop();
                dq.peek().append(str.reverse()); //peek method get the pointer of stringBuilder
            }else{
                dq.peek().append(c);
            }
        }

        return dq.poll().toString();
    }

    // Solution-2: 同样的解法处理括号内的运算 BasicCalculatorWithParenthesesIII.java
    public String reverseParenthesesRecursion(String s) {
        if(s == null || s.length() == 0) return s;
        return helper(s, 0, s.length()).toString();
    }

    public StringBuilder helper(String s, int left, int right) {
        StringBuilder sb = new StringBuilder();
        for(int i=left; i<right; i++) {
            char c = s.charAt(i);
            if(Character.isLetter(c)){
                sb.append(c);
            }else if(c == '('){
                int start = i, cnt=0;

                for(;i<right;i++){
                    if (s.charAt(i) == '(') cnt++;
                    if (s.charAt(i) == ')') cnt--;
                    if (cnt == 0) break;
                }
                StringBuilder subStr = helper(s, start+1, i);
                sb.append(subStr.reverse());
            }
        }

        return sb;
    }
}
