package category.Slidingwindow.templateSolution;

import java.util.HashSet;
/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters test = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(test.lengthOfLongestRepeatingSubstring("abcabcbb"));
    }

    public int lengthOfLongestRepeatingSubstring(String s) {
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