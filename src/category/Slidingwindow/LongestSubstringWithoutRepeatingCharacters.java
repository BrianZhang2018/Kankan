package category.Slidingwindow;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 
 * The time complexity is O(n) because:
 * Each character is visited at most twice: once by the right pointer when
 * expanding the window, and once by the left pointer when contracting it
 * Total operations: at most 2n character visits → O(n)
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, max = 0;
        while (right < s.length()) {
            if (set.add(s.charAt(right))) {
                max = Math.max(max, right - left + 1);
                right++;
            } else {
                set.remove(s.charAt(left++)); // shift left towards right until no duplicate
            }
        }

        return max;
    }

    public int lengthOfLongestSubstring1(String s) {
        if (s == null)
            return 0;
        if (s.length() == 1)
            return 1;

        int[] bucket = new int[128];
        int left = 0, right = 0;
        int max = 0;
        while (right < s.length()) {
            if (++bucket[s.charAt(right)] > 1) {
                while (left <= right && bucket[s.charAt(right)] > 1)
                    bucket[s.charAt(left++)]--;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }

        return max;
    }
}
