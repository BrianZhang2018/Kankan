package category.twoPointer;

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

        int i = 0, j = s.length() - 1;

        while (i < j) {
            while (i < s.length() && !Character.isLetterOrDigit(s.charAt(i)))
                i++;

            if (i > s.length()) return true;

            while (j >= 0 && !Character.isLetterOrDigit(s.charAt(j)))
                j--;

            if (j < 0) return true;

            if (Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--))) return false;
        }

        return true;
    }
}
