package category.slidingwindow.templateSolution;

import java.util.HashSet;

/**
 * Sliding window solution
 * related problem: https://www.techiedelight.com/sliding-window-problems/
 *
 * Leetcode 340 - Longest Substring with At Most K Distinct Characters
 * Created by brianzhang on 3/3/19.
 */
public class LongestSubstringWithUniqueCharacterSlidingWindow {
    public static void main(String[] args) {
        System.out.println(solution("nutdrgzdrkrvfdfcvzuunxwlzegjukhkjpvqruitobiahxhgdrpqttsebjsg", 11));
    }

    public static final int CHAR_RANGE = 128;
    //return the length
    public static int solution(String s, int k){
        
        int maxLength = Integer.MIN_VALUE;
        HashSet<Character> counter = new HashSet<Character>();
        int[] freq = new int[CHAR_RANGE]; //bucket array
        int left=0, right=0;
        while(right<s.length()){
            
            counter.add(s.charAt(right));
            freq[s.charAt(right)]++;
            right++;
            //slide the window
            while(counter.size() > k){
                if(--freq[s.charAt(left)] == 0){
                    counter.remove(s.charAt(left));
                }
                left++;
            }
            maxLength = Math.max(maxLength, right-left);
        }
  
        return maxLength;
    }
    
    // return the substring
    /*public static String solution(String s, int k){
        
        int begin=0, end =0;
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
            if(end - begin <high - low){
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