package category.Stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 *
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 * Created by brianzhang on 7/5/20.
 */
public class MinimumRemoveMakeValidParentheses {
    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("a)b(c)d"));
    }
    public static String minRemoveToMakeValid(String str) {
        StringBuilder sb = new StringBuilder(str);
        Stack<Integer> stack = new Stack<>(); // track the parentheses
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.add(i);
            } else if (str.charAt(i) == ')') {
                if (stack.isEmpty()){
                    sb.setCharAt(i, '*');
                }
                else {
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop(), '*');
        }

        return sb.toString().replaceAll("\\*", "");
    }
}
