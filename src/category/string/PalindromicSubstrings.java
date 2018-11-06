package category.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brianzhang on 8/11/18.
 */
public class PalindromicSubstrings {

    public static int countSubstrings(String s) {

        int res = 0;
        for (int i = 0; i < s.length(); i++) {

            res += count(s, i, i, res); //odd length
            res += count(s, i, i + 1, res); //even length
        }
        return res;
    }

    private static int count(String s, int l, int r, Integer res) {
        int temp = 0;

        while (l >= 0 && r < s.length() && s.charAt(l--) == s.charAt(r++)) {
            ++temp;
        }

        return temp;
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("abc"));

        char[] test = {'a', 'b', 'c', 'd'};

        System.out.println( Arrays.copyOfRange(test, 0, 2));
    }
}
