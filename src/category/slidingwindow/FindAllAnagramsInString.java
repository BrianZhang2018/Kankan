package category.slidingwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 438. find All Anagrams in a String
 * <p>
 * Created by brianzhang on 8/22/18.
 */
public class FindAllAnagramsInString {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;

        int[] hash = new int[256]; //character hash

        //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();

        while (right < s.length()) {
            //move right every time, if the character exists in p's hash[], decrease the count
            //current hash value >= 1 means the character is existing in p's hash[]
            if (hash[s.charAt(right)] >= 1) {
                count--;
            }
            hash[s.charAt(right)]--;
            right++;

            //when the count is down to 0, means we found the right anagram, add window's left to result
            if (count == 0) list.add(left);

            //if we find the window's size equals to target size, then we have to slid left to find the new match window
            //reset left as we have a new slide window
            //only increase the count if the character is in p
            if (right - left == p.length()) {

                //check if it's in the target string
                if (hash[s.charAt(left)] >= 0) {
                    count++;
                }
                //reset left( -1 or 0) to (0 or 1)
                hash[s.charAt(left)]++;
                left++;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }
}