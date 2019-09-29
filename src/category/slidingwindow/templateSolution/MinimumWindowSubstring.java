package category.slidingwindow.templateSolution;

/**
 * Duplicate one with SlidingWindowTemplateSolution.java
 * leetcode 76 
 * the tricky part is hwo to track the "ABC" which use the freq val to track
 * e.g 1->0->1
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
        for (int i = 0; i < t.length(); i++) {
            freq[t.charAt(i)]++;
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
                // sliding: increase left to find the character in "ABC"
                // increase the counter as we skip the character of the minStart of last minLen,
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
