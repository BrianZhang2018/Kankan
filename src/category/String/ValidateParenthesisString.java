package category.String;

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

    // solution 1
    public boolean checkValidString(String s) {
        if (s == null)
            return true;

        Stack<Integer> left = new Stack<>(), star = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left.push(i);
            } else if (c == '*') {
                star.push(i);
            } else if (c == ')') {
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

    // soluton 2
    public boolean checkValidString1(String s) {
        int cmin = 0, cmax = 0; // open parentheses count in range [cmin, cmax]
        for (char c : s.toCharArray()) {
            if (c == '(') {
                cmax++;
                cmin++;
            } else if (c == ')') {
                cmax--;
                cmin--;
            } else if (c == '*') {
                cmax++; // if `*` become `(` then openCount++
                cmin--; // if `*` become `)` then openCount--
                // if `*` become `` then nothing happens
                // So openCount will be in new range [cmin-1, cmax+1]
            }
            if (cmax < 0)
                return false; // Currently, don't have enough open parentheses to match close parentheses->
                              // Invalid
                              // For example: ())(
            cmin = Math.max(cmin, 0); // It's invalid if open parentheses count < 0 that's why cmin can't be negative
        }
        return cmin == 0; // Return true if can found `openCount == 0` in range [cmin, cmax]
    }
}
