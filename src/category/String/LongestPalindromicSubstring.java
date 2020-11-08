package category.String;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * Time complexity: O(n^2), Space complexity: O(1)
 *
 * Created by brianzhang on 8/11/18.
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring test = new LongestPalindromicSubstring();
        System.out.println(test.longestPalindrome("babad"));
    }

    public String longestPalindrome(String s) {
        HashMap<String, String> res = new HashMap();
        res.put("longest", "");
        for (int i = 0; i < s.length(); i++) {
            // assume odd length, try to extend Palindrome as possible
            count(s, i, i, res);
            // assume even length
            count(s, i, i + 1, res);
        }

        return res.get("longest");
    }

    public void count(String s, int left, int right, Map<String, String> res) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (res.get("longest").length() < right-left){
                res.put("longest", s.substring(left, right + 1));
            }
            left--;
            right++;
        }
    }
}
