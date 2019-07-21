package category.slidingwindow.RepeatingCharacter;

/**
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 * 
 * Created by brianzhang on 3/20/19.
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithAtLeastKRepeatingCharacters test = new LongestSubstringWithAtLeastKRepeatingCharacters();
        //System.out.println(test.longestSubstring("ababbc", 2));
        System.out.println(String.valueOf(123));
    }

    public int longestSubstring(String s, int k) {
        int d = 0;

        //brute force to loop all the situation, 1 unique character, 2 unique character, 3 character...
        //return the max one
     /*   for (int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++){
            int res = longestSubstringWithNUniqueChars(s, k, numUniqueTarget);
            System.out.println(numUniqueTarget + ": " + res);
            d = Math.max(d, res);
        }*/

        for (int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++){
            int res = longestKUniqueRepeatingCharacter(s, k, numUniqueTarget);
            //System.out.println(numUniqueTarget + ": " + res);
            d = Math.max(d, res);
        }

        return d;
    }

    private int longestSubstringWithNUniqueChars(String s, int k, int numUniqueTarget) {
        int[] map = new int[128];
        // counter 1, number of unique character
        int numUnique = 0;
        // counter 2, number of character which frequent is more than K
        int numNoLessThanK = 0;
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

    public int longestKUniqueRepeatingCharacter(String s, int k, int cNum){
        int low = 0, high = 0;
        int[] repeatNum = new int[26];
        int uniqueCNum = 0;
        int uniqueKNum = 0;
        int res = 0;

        while(high < s.length()){
            Character c = s.charAt(high);
            if(repeatNum[c-'a']++ == 0)
                uniqueCNum++;
            if(repeatNum[c-'a'] == k)
                uniqueKNum++;

            while(uniqueCNum>cNum){
                Character cl = s.charAt(low);
                if(repeatNum[cl-'a']-- == k){
                    uniqueKNum--;
                }
                if(repeatNum[cl-'a'] == 0){
                    uniqueCNum--;
                }
                low++;
            }
            high++;
            if(uniqueCNum == cNum && uniqueCNum == uniqueKNum){
                res = Math.max(high-low, res);
            }
        }
        return res;
    }
}
