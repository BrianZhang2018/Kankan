package category.slidingwindow.templateSolution;

/**
 * leetcode 76 
 * the tricky part is hwo to track the "ABC" which use the freq val to track
 * e.g 1->0->1   initial freq `A` is 1, and minus 1 when found a `A`, then slide to re-increase to 1
 *     1->0->-1->0->1
 *
 * https://briansuperzhang.gitbook.io/project/sliding-minLen-questions
 * 
 * solution reference: (but I didn't follow this when I solved this question, just for reference)
 * https://leetcode.com/problems/minimum-minLen-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
 * 
 * Created by brianzhang on 3/24/19.
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("DAOBECEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        int[] freq = new int[128]; //bucket array frequency of character
        for (char c : t.toCharArray()) {
            freq[c]++;
        }

        int counter = t.length(), left = 0, right = 0, minStart = -1, minLen = Integer.MAX_VALUE;
        while (right < s.length()) {
            char rc = s.charAt(right);
            if (freq[rc] > 0) {
                counter--;
            }
            freq[rc]--;
            right++;
            
            //slide minLen by recover the counter(++) and move the start(++)
            while (counter == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    minStart = left;
                }
                // Sliding: increase left to find the character in "ABC",
                // if we find one of target character (freq[lc] == 0), then increase the counter as the pointer is left++.
                char lc = s.charAt(left++);
                if (freq[lc] == 0) {
                    counter++;
                }
                freq[lc]++;
            }
        }
        return minStart == -1 ? "" : s.substring(minStart, minStart + minLen);
    }
}
