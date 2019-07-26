package category.slidingwindow;

/**
 * leetcode 76
 * the hard part is hwo to track the "ABC" which use the bucket val to track
 * e.g 1->0->1
 *     1->0->-1->0->1
 *
 * https://briansuperzhang.gitbook.io/project/sliding-window-questions
 *
 * Created by brianzhang on 3/24/19.
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(minWindow("DAOBECEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        int[] arr = new int[128];
        for (int i = 0; i < t.length(); i++) {
            arr[t.charAt(i)]++;
        }

        int counter = t.length(), left = 0, right = 0, window = Integer.MAX_VALUE, begin = -1;
        while (right < s.length()) {
            char rc = s.charAt(right++);
            if (arr[rc] > 0) {
                counter--;
            }
            arr[rc]--;

            while (counter == 0) {
                if (right - left < window) {
                    window = right - left;
                    begin = left;
                }
                // sliding: increase left to find the character in "ABC"
                // 1: get the correct window range
                // 2: increase the counter as we skip the character of the begin of last window,
                // then slide (r) to find it in the right substring to make a new satisfied window
                char lc = s.charAt(left++);
                if (arr[lc] == 0) {
                    counter++;
                }
                arr[lc]++;
            }
        }
        return begin == -1 ? "" : s.substring(begin, begin + window);
    }
}
