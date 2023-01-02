package category.Slidingwindow.templateSolution.template1;

/**
 * Leetcode 340 - Longest Substring with At Most K Distinct Characters
 * https://www.lintcode.com/problem/longest-substring-with-at-most-k-distinct-characters/description
 *
 * Sliding window + freq bucket:
 * 1. two pointer: left, right
 * 2. freq count bucket: new int[]
 * 3. shift window
 *
 * Microsoft
 * Created by brianzhang on 3/3/19.
 */
public class LongestSubstringAtMostKDistinctCharacter {
    public static void main(String[] args) {
        System.out.println(solution("abc", 2));
    }
    // standard sliding window solution
    public static int solution(String s, int k) {
        int[] freq = new int[128]; // 128, ASCII range, can be improved by 26 for sure
        int left=0, right=0, max = 0;
        while(right < s.length()){
            char c = s.charAt(right++);
            freq[c]++;
            if (freq[c] == 1) k--;
            if (k == 0) max = Math.max(right - left, max);
            if (k < 0) {
                while(--freq[s.charAt(left++)] != 0) {}
                k++;
            }
        }
        return max > right - left ? max : right -left;
    }

    // Simple, but a little tricky solution
    public static int solution1(String s, int k) {
        int[] freq = new int[128]; // 128, ASCII range
        int left=0, right=0;
        while(right < s.length()){
            char c = s.charAt(right++);
            freq[c]++;
            if (freq[c] == 1) k--;

            // shift from left, but the window size won't be changed as we did right++ above, 所以是平行移动, e.g. 2->5 to 3->6
            // the window size will be changed only when found bigger window (重点思路)
            if (k<0 && --freq[s.charAt(left++)] == 0) { // if k>= 0, the latter condition won't be executed which means left won't move, but window size increased
                k++;
            }
        }
        return right-left;
    }

}
