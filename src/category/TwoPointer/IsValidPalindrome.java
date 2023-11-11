package category.TwoPointer;

/**
 * https://leetcode.com/problems/valid-palindrome/
 *
 * Created by brianzhang on 8/9/20.
 */
public class IsValidPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) return true;

        int l = 0, r = s.length() - 1;
        char head, tail;
        while (l <= r) {
            head = s.charAt(l);
            tail = s.charAt(r);
            if (!Character.isLetterOrDigit(head)) {
                l++;
            } else if (!Character.isLetterOrDigit(tail)) {
                r--;
            } else {
                if (Character.toLowerCase(head) != Character.toLowerCase(tail)) {
                    return false;
                }
                l++;
                r--;
            }
        }

        return true;
    }
}
