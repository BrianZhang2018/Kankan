package category.string;

import java.util.HashSet;

/**
 * Sliding window Asolution
 * related problem: https://www.techiedelight.com/sliding-window-problems/
 *
 * Leetcode 340
 * Created by brianzhang on 3/3/19.
 */
public class LongestSubstringWithUniqueCharacterSlidingWindow {

    public static void main(String[] args) {
        System.out.println(solution("nutdrgzdrkrvfdfcvzuunxwlzegjukhkjpvqruitobiahxhgdrpqttsebjsg", 11));
    }

    public static final int CHAR_RANGE = 128;

    public static String solution(String s, int k){
        int begin=0, end =0;
        HashSet<Character> window = new HashSet<Character>();
        int[] freq = new int[CHAR_RANGE];
        for(int low=0, high=0; high<s.length(); high++){
            window.add(s.charAt(high));
            freq[s.charAt(high)]++;

            while(window.size() > k){
                if(--freq[s.charAt(low)] == 0){
                    window.remove(s.charAt(low));
                }
                low++;
            }

            if(end - begin <high - low){
                end = high;
                begin = low;
            }
        }

        return s.substring(begin, end+1);
    }

    //Simple version
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] charset = new int[128]; // assume ASCII
        int maxLen = 0;
        for (int lo = 0, hi = 0, distinct = 0; hi < s.length(); hi++) {
            char letter = s.charAt(hi);
            charset[letter]++;
            if (charset[letter] == 1) distinct++;

            if (distinct > k) {
                letter = s.charAt(lo++);
                charset[letter]--;
                if (charset[letter] == 0)
                    distinct--;
            }

            maxLen = hi - lo + 1;
        }
        return maxLen;
    }

    class Solution {
        public int longestSubstring(String s, int k) {
            int d = 0;

            for (int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++)
                d = Math.max(d, longestSubstringWithNUniqueChars(s, k, numUniqueTarget));

            return d;
        }

        private int longestSubstringWithNUniqueChars(String s, int k, int numUniqueTarget) {
            int[] map = new int[128];
            int numUnique = 0; // counter 1, number of unique character
            int numNoLessThanK = 0; // counter 2, number of character which frequent is more than K
            int begin = 0, end = 0;
            int d = 0;

            while (end < s.length()) {
                if (map[s.charAt(end)]++ == 0) numUnique++; // increment map[c] after this statement
                if (map[s.charAt(end++)] == k) numNoLessThanK++; // inc end after this statement

                while (numUnique > numUniqueTarget) {
                    if (map[s.charAt(begin)]-- == k) numNoLessThanK--; // decrement map[c] after this statement
                    if (map[s.charAt(begin++)] == 0) numUnique--; // inc begin after this statement
                }

                // if we found a string where the number of unique chars equals our target
                // and all those chars are repeated at least K times then update max
                if (numUnique == numUniqueTarget && numUnique == numNoLessThanK)
                    d = Math.max(end - begin, d);
            }

            return d;
        }
    }
}
