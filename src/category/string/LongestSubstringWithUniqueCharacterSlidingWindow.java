package category.string;

import java.util.HashSet;

/**
 * Sliding window solution
 * related problem: https://www.techiedelight.com/sliding-window-problems/
 *
 * Leetcode 340
 * Created by brianzhang on 3/3/19.
 */
public class LongestSubstringWithUniqueCharacterSlidingWindow {

    public static void main(String[] args) {
        System.out.println(solution("nutdrgzdrkrvfdfcvzuunxwlzegjukhkjpvqruitobiahxhgdrpqttsebjsg", 11));

        System.out.println(26&15);
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
                if (charset[letter] == 0) distinct--;
            }

            maxLen = hi - lo + 1;
        }
        return maxLen;
    }
}
