package category.slidingwindow.templateSolution;

import java.util.HashSet;
/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters test = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(test.lengthOfLongestRepeatingSubstring("abcabcbb"));
    }

    //simple version
    public int lengthOfLongestRepeatingSubstring(String s) {
        if(s == null || s.length() == 0)
            return 0;

        int left=0, right=0, maxLen = Integer.MIN_VALUE;
        HashSet<Character> counter = new HashSet<Character>();
        //although didn't copy the template exactly, but the below logic is simpler
        while(left<s.length() && right<s.length()){
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