package category.Slidingwindow.templateSolution;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 438. find All Anagrams in a String
 *
 * clarification: Find the anagram substring of target string
 *
 * Created by brianzhang on 8/22/18.
 */
public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebacd", "abc"));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) 
            return list;

        //the character frequency of target string
        int[] freq = new int[26];
        for (char c : p.toCharArray()) {
            freq[c-'a']++;
        }

        int left = 0, right = 0, count = p.length();

        while (right < s.length()) {
            // move right every time, if the character exists in p's freq[], decrease the count
            // current freq value >= 1 means the character is existing in p's freq[]
            char rc = s.charAt(right);
            if (freq[rc - 'a'] >= 1) {
                count--;
            }
            freq[rc-'a']--;
            right++;

            // when the count is down to 0 which means found the anagram, so add window's left to result
            if (count == 0) list.add(left);

            //if we find the window's size equals to target size, then we need to slide the window (left++) to look for next match window
            //increase the count if the character is in target
            if (right - left == p.length()) {
                char lc = s.charAt(left);
                if (freq[lc - 'a'] >= 0) {  // means character in the target string. if less than 0, like -1, means not in target string, don't need increase count.
                    count++;
                }
                //reset left (-1 to 0: if left character not in target), (0 to 1) if left character in the target
                freq[lc - 'a']++;
                left++;
            }
        }
        return list;
    }
}