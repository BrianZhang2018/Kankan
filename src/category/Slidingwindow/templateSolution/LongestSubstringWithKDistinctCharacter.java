package category.Slidingwindow.templateSolution;

import java.util.HashSet;

/**
 * Sliding window solution
 * related problem: https://www.techiedelight.com/sliding-window-problems/
 *
 * https://www.lintcode.com/problem/longest-substring-with-at-most-k-distinct-characters/description
 * Leetcode 340 - Longest Substring with At Most K Distinct Characters
 * Created by brianzhang on 3/3/19.
 */
public class LongestSubstringWithKDistinctCharacter {
    public static void main(String[] args) {
        System.out.println(solution("nutdrgzdrkrvfdfcvzuunxwlzegjukhkjpvqruitobiahxhgdrpqttsebjsg", 11));
    }

    public static final int CHAR_RANGE = 128;
    //return the length - template solution
    public static int solution(String s, int k){
        
        int maxLength = Integer.MIN_VALUE;
        HashSet<Character> counter = new HashSet<>();
        int[] freq = new int[CHAR_RANGE]; //bucket array
        int left=0, right=0;
        while(right<s.length()){
            char rc = s.charAt(right);
            counter.add(rc);
            freq[rc]++;
            right++;

            //slide the window
            while(counter.size() > k){
                char lc = s.charAt(left++);
                if(--freq[lc] == 0){
                    counter.remove(lc);
                }
            }
            maxLength = Math.max(maxLength, right-left);
        }
  
        return maxLength;
    }
    
    // Get the substring, https://www.techiedelight.com/sliding-window-problems/
    /*public static String solution(String s, int k){
        
        int begin=0, end =0; // just need this additional two pointer to store the position compare with above solution
        HashSet<Character> window = new HashSet<Character>();
        int[] freq = new int[CHAR_RANGE];
        int low=0, high=0;
        while(high<s.length()){
            
            window.add(s.charAt(high));
            freq[s.charAt(high)]++;

            while(window.size() > k){
                if(--freq[s.charAt(low)] == 0){
                    window.remove(s.charAt(low));
                }
                low++;
            }
            if(end - begin < high - low){
                end = high;
                begin = low;
            }

            high++;
        }
  
        return s.substring(begin, end+1);
    } */

    //Simple version
   /* public static String lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] charset = new int[128]; // assume ASCII
        String res = null;
        for (int lo = 0, hi = 0, distinct = 0; hi < s.length(); hi++) {
            char letter = s.charAt(hi);
            charset[letter]++;
            if (charset[letter] == 1)
                distinct++;

            if (distinct > k) {
                letter = s.charAt(lo++);
                charset[letter]--;
                if (charset[letter] == 0)
                    distinct--;
            }
            res = s.substring(lo, hi + 1);
        }
        return res;
    }*/
}
