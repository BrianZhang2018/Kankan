package category.string;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/palindromic-substrings/
 *
 * Created by brianzhang on 8/11/18.
 */
public class PalindromicSubstrings {

    public static void main(String[] args) {
        PalindromicSubstrings ps = new PalindromicSubstrings();
        System.out.println(ps.countSubstrings("abc"));

        char[] test = {'a', 'b', 'c', 'd'};

        System.out.println( Arrays.copyOfRange(test, 0, 2));
    }

    public int countSubstrings(String s) {

        int res = 0;
        for (int i = 0; i < s.length(); i++) {

            res += count(s, i, i, res); //odd length
            res += count(s, i, i + 1, res); //even length
        }
        return res;
    }

    private int count(String s, int l, int r, Integer res) {
        int temp = 0;

        while (l >= 0 && r < s.length() && s.charAt(l--) == s.charAt(r++)) {
            ++temp;
        }

        return temp;
    }
}
