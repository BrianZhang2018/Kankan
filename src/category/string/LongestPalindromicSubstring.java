package category.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brianzhang on 8/11/18.
 *
 * Time complexity: O(n^2), Space complexity: O(1)
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));

    }

    public static String longestPalindrome(String s) {
        HashMap<String, String> res = new HashMap();
        res.put("longest", "");
        for (int i = 0; i < s.length(); i++) {
            // get longest palindrome with center of i
            count(s, i, i, res);
            // get longest palindrome with center of i, i+1
            count(s, i, i + 1, res);
        }

        return res.get("longest");
    }

    public static void count(String s, int left, int right, Map<String, String> res) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (res.get("longest").length() < right-left){
                res.put("longest", s.substring(left, right + 1));
            }
            left--;
            right++;
        }
    }
}
