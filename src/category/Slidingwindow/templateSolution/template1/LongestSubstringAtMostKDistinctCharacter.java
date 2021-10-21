package category.Slidingwindow.templateSolution.template1;

/**
 * https://www.lintcode.com/problem/longest-substring-with-at-most-k-distinct-characters/description
 * Leetcode 340 - Longest Substring with At Most K Distinct Characters
 *
 * Sliding window solution:
 * 1. two pointer: left, right
 * 2. freq count bucket: new int[]
 * 3. shifting window
 *
 * Microsoft
 * Created by brianzhang on 3/3/19.
 */

public class LongestSubstringAtMostKDistinctCharacter {
    public static void main(String[] args) {
        System.out.println(solution("abc", 2));
        //System.out.println(LongestSubstringAtMostKDistinctCharacter("abc", 2));
    }

    // Simple and better solution - template with freq accumulation
    public static int solution(String s, int k) {
        int[] freq = new int[128]; // 128, ASCII range
        int left=0, right=0;
        while(right < s.length()){
            char c = s.charAt(right++);
            freq[c]++;
            if (freq[c] == 1) k--;

            // shift from left, but the window size won't be changed as we did right++ above, 所以是平行移动, e.g. 2->5 to 3->6
            // the window size will be changed only when found bigger window (重点思路)
            if (k<0 && --freq[s.charAt(left++)] == 0) { // if k>= 0, the latter condition won't be executed so that left won't move, the window size increased
                k++;
            }
        }

        return right-left;
    }

    // template solution 2
/*    public static int LongestSubstringAtMostKDistinctCharacter(String s, int k){
        
        int maxLength = Integer.MIN_VALUE;
        Set<Character> counter = new HashSet<>(); // count the number of unique character
        int[] freq = new int[128];   // accumulate the frequency of each character
        int left=0, right=0;
        while(right<s.length()){
            char rc = s.charAt(right++);
            counter.add(rc);
            freq[rc]++;

            //slide the window
            while(counter.size() > k){
                char lc = s.charAt(left++); // shift from left until reach the valid window
                if(--freq[lc] == 0){
                    counter.remove(lc);
                }
            }
            maxLength = Math.max(maxLength, right-left);
        }
  
        return maxLength;
    }*/

    // https://www.techiedelight.com/sliding-window-problems/
    /*public static String solution(String s, int k){
        
        int begin=0, end =0; // just need this additional two pointer to store the position compare with above solution
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
            if(end - begin < high - low){
                end = high;
                begin = low;
            }

            high++;
        }
  
        return s.substring(begin, end+1);
    } */
}
