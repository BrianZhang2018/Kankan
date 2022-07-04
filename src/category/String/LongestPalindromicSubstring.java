package category.String;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * Time complexity: O(n^2), Space complexity: O(1)
 *
 * better performance than dp solution actually
 * Created by brianzhang on 8/11/18.
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring test = new LongestPalindromicSubstring();
        System.out.println(test.longestPalindrome("babad"));
        System.out.println("".length());
    }

    String maxStr = "";
    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            count(s, i, i); // assume odd length, try to extend Palindrome as possible
            count(s, i, i + 1);  // assume even length
        }
        return maxStr;
    }

    public void count(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (maxStr.length() <= right-left){
                maxStr = s.substring(left, right + 1);
            }
            left--;
            right++;
        }
    }
}
