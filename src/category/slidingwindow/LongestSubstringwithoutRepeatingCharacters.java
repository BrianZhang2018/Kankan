package category.slidingwindow;

import java.util.HashSet;

public class LongestSubstringwithoutRepeatingCharacters {

    public static void main(String[] args) {
        LongestSubstringwithoutRepeatingCharacters test = new LongestSubstringwithoutRepeatingCharacters();
        System.out.println(test.lengthOfLongestSubstring("abcabcbb"));
    }
    
    public int lengthOfLongestSubstring(String s) {
        if(s== null || s.length() == 0)
            return 0;
        int max = 0;
        
        int i=0, j=0;
        HashSet<Character> set = new HashSet<Character>();
        while(i<s.length() && j<s.length()){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                max=Math.max(max, j-i);
            }else{
                set.remove(s.charAt(i++));
            }
        }
        
        return max;
    }
}