package category.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 *
 * Created by brianzhang on 2/17/20.
 */
public class ReverseSubStringsBetweenPairedParentheses {

    public static void main(String[] args) {
        System.out.println(reverseParentheses("super(gnod(zhang))"));
    }

    public static String reverseParentheses(String s) {
        Deque<StringBuilder> dq = new ArrayDeque<>();
        dq.add(new StringBuilder());

        for(char c : s.toCharArray()){
            if(c == '('){
                dq.add(new StringBuilder());
            }else if(c == ')'){
                StringBuilder str = dq.pollLast();
                dq.peekLast().append(str.reverse()); //peek method get the pointer of stringBuilder
            }else{
                dq.peekLast().append(c);
            }
        }

        return dq.pollLast().toString();
    }
}
