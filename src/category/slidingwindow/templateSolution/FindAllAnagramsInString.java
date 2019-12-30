package category.slidingwindow.templateSolution;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 438. find All Anagrams in a String
 *
 * clarification: Find the anagram substring of target string
 *
 * Created by brianzhang on 8/22/18.
 */
public class FindAllAnagramsInString {
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) 
            return list;

        int[] freq = new int[26]; //character freq

        //the frequency of target string
        for (char c : p.toCharArray()) {
            freq[c-'a']++;
        }

        int left = 0, right = 0, count = p.length();

        while (right < s.length()) {
            //move right every time, if t频率he character exists in p's freq[], decrease the count
            //current freq value >= 1 means the character is existing in p's freq[]
            char rc = s.charAt(right);
            if (freq[rc - 'a'] >= 1) {
                count--;
            }
            freq[rc-'a']--;
            right++;

            //when the count is down to 0, which means we found the right anagram, so add window's left to result
            if (count == 0) 
                list.add(left);

            //if we find the window's size equals to target size, then we have to slide left++ to find the new match window
            //increase the count if the character is in p
            if (right - left == p.length()) {
                char lc = s.charAt(left);
                //check if it's in the target string
                if (freq[lc - 'a'] >= 0) {
                    count++;
                }
                //reset left( -1 or 0) to (0 or 1)
                freq[lc - 'a']++;
                left++;
            }
        }
        return list;
    }
}