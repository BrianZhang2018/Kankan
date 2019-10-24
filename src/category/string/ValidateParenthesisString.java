package category.string;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parenthesis-string/
 * Created by brianzhang on 3/2/19.
 */
public class ValidateParenthesisString {
    public static void main(String[] args) {
        ValidateParenthesisString test = new ValidateParenthesisString();
        System.out.println(test.checkValidString("((*)"));
    }

    public boolean checkValidString(String s) {
        if (s == null)
            return true;

        Stack<Integer> left = new Stack<Integer>();
        Stack<Integer> star = new Stack<Integer>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (c == '(') {
                left.push(i);
            } else if (c == '*') {
                star.push(i);
            } else {
                if (left.isEmpty() && star.isEmpty())
                    return false;

                if (!left.isEmpty()) {
                    left.pop();
                } else if (!star.isEmpty()) {
                    star.pop();
                }
            }
        }

        while (!left.isEmpty() && !star.isEmpty()) {
            if (left.pop() > star.pop())
                return false;
        }

        return left.isEmpty();
    }
}
