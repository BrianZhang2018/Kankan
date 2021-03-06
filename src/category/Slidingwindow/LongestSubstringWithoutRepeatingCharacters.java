package category.Slidingwindow;

import java.util.HashSet;
/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestRepeatingSubstring("abcabcbb"));
    }

    public static int lengthOfLongestRepeatingSubstring(String s) {
        if(s == null || s.length() == 0) return 0;

        int left=0, right=0, maxLen = Integer.MIN_VALUE, n = s.length();
        HashSet<Character> counter = new HashSet<>();

        while(left<n && right<n){
            if(!counter.contains(s.charAt(right))){
                counter.add(s.charAt(right++));
                maxLen=Math.max(maxLen, right-left);
            }else{
                counter.remove(s.charAt(left++));
            }
        }
        
        return maxLen;
    }
}