package category.string;

import java.util.HashSet;

/**
 * Created by brianzhang on 3/3/19.
 */
public class LongestSubstringWithUniqueCharacterSlidingWindow {

    public static void main(String[] args) {
        System.out.println(solution("aabbcc", 3));
    }

    public static final int CHAR_RANGE = 128;

    public static String solution(String s, int k){
        int start=0, end =0;
        HashSet<Character> window = new HashSet<Character>();
        int[] freq = new int[CHAR_RANGE];
        for(int low=0, high=0; high<s.length(); high++){
            window.add(s.charAt(high));
            freq[s.charAt(high)]++;

            if(window.size() > k){
                if(--freq[s.charAt(high)] == 0)
                    window.remove(s.charAt(high));

                low++;
            }

            if(end - start <high - low){
                end = high;
                start = low;
            }
        }

        return s.substring(start, end+1);
    }

}
