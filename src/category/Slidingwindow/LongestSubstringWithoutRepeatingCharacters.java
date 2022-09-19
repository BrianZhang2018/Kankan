package category.Slidingwindow;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet();
        int left = 0, right = 0, max = 0;
        while (right < s.length()) {
            if (set.add(s.charAt(right))) {
                max = Math.max(max, right - left + 1);
                right++;
            } else {
                set.remove(s.charAt(left++)); // shift left
            }
        }

        return max;
    }
}