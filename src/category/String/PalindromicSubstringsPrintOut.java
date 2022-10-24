package category.String;

import java.util.HashSet;
import java.util.Set;
/**
 * Solution: For each character in the given string, we consider it as mid point of a palindrome and expand in both directions to
 * find all palindromes that have it as mid-point.
 *
 * https://www.techiedelight.com/find-possible-palindromic-substrings-string/
 *
 * related problem:
 * get the number of palindrome substring: https://leetcode.com/problems/palindromic-substrings/
 *
 * DP solution:
 * https://www.geeksforgeeks.org/count-palindrome-sub-strings-string/
 */

class PalindromicSubstringsPrintOut {
    public static void main(String[] args) {
        String str = "google";
        allPalindromicSubStrings(str);
    }
    // expand in both directions of low and high to find all palindromes
    public static void expand(String str, int low, int high, Set<String> set) {
        // run till str[low.high] is a palindrome
        while (low >= 0 && high < str.length()
                && str.charAt(low) == str.charAt(high)) {
            // push all palindromes into the set
            set.add(str.substring(low, high + 1));
            // expand in both directions
            low--;
            high++;
        }
    }

    // Function to find all unique palindromic substrings of given string
    public static void allPalindromicSubStrings(String str) {
        // create an empty set to store all unique palindromic substrings
        Set<String> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            // find all odd length palindrome with str[i] as mid point
            expand(str, i, i, set);
            // find all even length palindrome with str[i] and str[i+1] as its mid points
            expand(str, i, i + 1, set);
        }

        // print all unique palindromic substrings
        System.out.print(set);
    }

}

